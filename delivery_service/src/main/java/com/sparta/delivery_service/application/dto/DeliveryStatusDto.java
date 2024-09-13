package com.sparta.delivery_service.application.dto;

import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryStatusDto {
    private UUID deliveryId;
    private DeliveryStatus deliveryStatus;

    public static DeliveryStatusDto of(UUID deliveryId, DeliveryStatus deliveryStatus) {
        return DeliveryStatusDto.builder()
            .deliveryId(deliveryId)
            .deliveryStatus(deliveryStatus)
            .build();
    }
}

