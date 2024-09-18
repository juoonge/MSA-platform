package com.sparta.delivery_service.application.dto.deliverydto;

import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class DeliveryCreateRequest {

    private UUID orderId;
    private UUID vendorId;
    private UUID startHubId;
    private UUID endHubId;
    private String address;
    private List<UUID> hubPaths;
}

