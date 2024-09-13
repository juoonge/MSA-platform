package com.sparta.delivery_service.application.dto;

import java.util.List;
import lombok.Getter;
import java.util.UUID;

@Getter
public class DeliveryCreateRequest {
    private UUID orderId;
    private UUID vendorId;
    private UUID startHubId;
    private UUID endHubId;
    private String address;
    private List<UUID> hubPaths;
}

