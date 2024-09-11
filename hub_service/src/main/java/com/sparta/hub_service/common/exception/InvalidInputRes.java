package com.sparta.hub_service.common.exception;

public record InvalidInputRes(
    String field,
    String message
) {

}