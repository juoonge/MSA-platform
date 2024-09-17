package com.example.vendor._common;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import feign.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse> feignException(FeignException e) throws JsonProcessingException {
        log.warn(e.getMessage());
        String responseJson = e.contentUTF8();
        Map<String, String> responseMap = objectMapper.readValue(responseJson, Map.class);
        String message = responseMap.get("message");
        return new ResponseEntity<>(ApiResponse.fail(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiException(ApiException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(ApiResponse.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> exception(Exception e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(ApiResponse.fail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
