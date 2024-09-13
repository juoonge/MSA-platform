package com.example.vendor._common;

import lombok.*;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private String message;

}
