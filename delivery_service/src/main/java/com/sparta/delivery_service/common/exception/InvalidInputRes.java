package com.sparta.delivery_service.common.exception;

public record InvalidInputRes(
    String field,
    String message
) {

}