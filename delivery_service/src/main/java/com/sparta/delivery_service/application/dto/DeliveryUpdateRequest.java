package com.sparta.delivery_service.application.dto;

import java.util.UUID;
import lombok.Getter;

@Getter
public class DeliveryUpdateRequest {
    private String address;
    private UUID startHubId;
    private UUID endHubId;
}

