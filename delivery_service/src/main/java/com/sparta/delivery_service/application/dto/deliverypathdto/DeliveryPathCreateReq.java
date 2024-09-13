package com.sparta.delivery_service.application.dto.deliverypathdto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 배송 경로 생성 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPathCreateReq {

    private UUID deliveryId;

    private UUID hubId;

    private Integer sequenceNumber;

    private Integer estimatedDuration;

    private Double actualDistance;

    private Integer actualDuration;
}
