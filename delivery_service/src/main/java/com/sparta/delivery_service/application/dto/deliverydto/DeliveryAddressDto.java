package com.sparta.delivery_service.application.dto.deliverydto;

import com.sparta.delivery_service.domain.entity.Delivery;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

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
