package com.sparta.delivery_service.application.dto.deliverydto;

import com.sparta.delivery_service.domain.entity.Delivery;
import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class DeliveryRes {

    private UUID deliveryId;
    private UUID orderId;
    private UUID vendorId;
    private UUID startHubId;
    private UUID endHubId;
    private String address;
    private DeliveryStatus deliveryStatus;

    public static DeliveryRes of(Delivery delivery) {
        return DeliveryRes.builder()
            .deliveryId(delivery.getDeliveryId())
            .orderId(delivery.getOrderId())
            .vendorId(delivery.getVendorId())
            .startHubId(delivery.getStartHubId())
            .endHubId(delivery.getEndHubId())
            .address(delivery.getAddress())
            .deliveryStatus(delivery.getDeliveryStatus())
            .build();
    }
}