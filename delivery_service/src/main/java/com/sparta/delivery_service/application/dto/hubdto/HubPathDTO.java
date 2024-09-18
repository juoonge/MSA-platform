package com.sparta.delivery_service.application.dto.hubdto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HubPathDTO implements Serializable {

    private UUID hubPathId;
    private UUID startHubId;
    private UUID endHubId;
    private BigDecimal duration;
    private Integer sequenceNumber;

    public HubPathDTO(Integer sequenceNumber, UUID startHubId) {
        this.sequenceNumber = sequenceNumber;
        this.startHubId = startHubId;
    }

}

