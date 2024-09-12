package com.example.product._common;

import lombok.*;

@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String message;

}
