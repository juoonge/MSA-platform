package com.sparta.auth.infrastructure.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiResponse {
    private int statusCode;
    private String status;
    private String message;
    private Object data;
}
