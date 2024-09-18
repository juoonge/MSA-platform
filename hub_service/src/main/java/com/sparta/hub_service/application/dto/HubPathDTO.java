package com.sparta.hub_service.application.dto;

import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HubPathDTO implements Serializable {

    private UUID hubPathId;
    private UUID startHubId;
    private UUID endHubId;
    private BigDecimal duration;
    private Integer sequenceNumber;
}

