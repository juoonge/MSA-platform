package com.example.vendor._common;

import lombok.*;

@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String message;

}
