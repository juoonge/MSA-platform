package com.sparta.delivery_service.application.dto.deliverypathdto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 배송 경로 생성 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryPathCreateReq {

    private UUID deliveryId;

    private UUID hubId;

    private Integer sequenceNumber;

    private BigDecimal estimatedDuration;

    private Double actualDistance;

    private BigDecimal actualDuration;
}
