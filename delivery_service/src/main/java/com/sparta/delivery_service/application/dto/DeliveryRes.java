package com.sparta.delivery_service.application.dto;

import com.sparta.delivery_service.domain.entity.Delivery;
import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import java.util.UUID;
import lombok.Data;

@Data
public class DeliveryRes {
    private UUID deliveryId;
    private UUID orderId;
    private UUID vendorId;
    private UUID startHubId;
    private UUID endHubId;
    private String address;
    private DeliveryStatus deliveryStatus;

    public DeliveryRes(Delivery delivery) {
        this.deliveryId = delivery.getDeliveryId();
        this.orderId = delivery.getOrderId();
        this.vendorId = delivery.getVendorId();
        this.startHubId = delivery.getStartHubId();
        this.endHubId = delivery.getEndHubId();
        this.address = delivery.getAddress();
        this.deliveryStatus = delivery.getDeliveryStatus();
    }
}