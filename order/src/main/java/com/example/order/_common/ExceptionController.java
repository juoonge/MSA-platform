package com.example.order._common;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._event.*;
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

    private final ProductService productService;
    private final KafkaUtils kafkaUtils;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ApiResponse> feignException(FeignException e) throws JsonProcessingException {
        log.warn(e.getMessage());
        String responseJson = e.contentUTF8();
        Map<String, String> responseMap = objectMapper.readValue(responseJson, Map.class);
        String message = responseMap.get("message");
        return new ResponseEntity<>(ApiResponse.fail(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderRegisterException.class)
    public ResponseEntity<ApiResponse> orderRegisterException(OrderRegisterException e) {
        log.warn(e.getMessage());
        productService.increaseStock(e.getOrderProductId(), new ChangeStockReq(e.getAmount()));
//        kafkaUtils.sendEvent("order-cancel", new OrderCancelEvent(e.getOrderProductId(), e.getAmount()));
        return new ResponseEntity<>(ApiResponse.fail(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
