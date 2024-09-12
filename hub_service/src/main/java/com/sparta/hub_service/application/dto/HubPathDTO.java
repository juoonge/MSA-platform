package com.sparta.hub_service.application.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HubPathDTO {

    private UUID hubPathId;
    private Long startHubId;
    private Long endHubId;
    private BigDecimal duration;
}

