package com.sparta.delivery_service.application.dto;

import com.sparta.delivery_service.domain.entity.Delivery;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
public class DeliveryAddressDto {
    private UUID deliveryId;
    private String address;

    public static DeliveryAddressDto of(Delivery delivery) {
        return DeliveryAddressDto.builder()
            .deliveryId(delivery.getDeliveryId())
            .address(delivery.getAddress())
            .build();
    }
}
