package com.example.order._common;

import lombok.*;

import java.util.*;

@Getter
public class OrderRegisterException extends ApiException {

    private UUID orderProductId;
    private Long amount;

    public OrderRegisterException(UUID orderProductId, Long amount, String message) {
        super(message);
        this.orderProductId = orderProductId;
        this.amount = amount;
    }
}
