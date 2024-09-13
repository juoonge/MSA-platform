package com.sparta.delivery_service.application.dto.deliverydto;

import java.util.UUID;
import lombok.Data;

@Data
public class DeliveryDTO {

    private UUID orderId;

    private UUID vendorId;

    private UUID startHubId;

    private UUID endHubId;

    private String address;
}