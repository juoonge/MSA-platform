package com.example.order._common;

import lombok.*;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String message;

}
