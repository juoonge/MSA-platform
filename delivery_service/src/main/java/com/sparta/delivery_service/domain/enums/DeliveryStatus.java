package com.sparta.delivery_service.domain.enums;

import lombok.Getter;

@Getter
public enum DeliveryStatus {
    PENDING("허브 대기중"),
    IN_TRANSIT("허브 이동중"),
    ARRIVED_AT_DESTINATION("목적지 허브 도착"),
    SHIPPED("배송중"),
    DELIVERED("목적지 도착");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

}

