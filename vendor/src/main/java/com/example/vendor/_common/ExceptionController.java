package com.example.vendor._common;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ApiException.class)
    public static ApiResponse apiException(ApiException e) {
        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public static ApiResponse exception(Exception e) {
        return ApiResponse.error(e.getMessage());
    }

}
