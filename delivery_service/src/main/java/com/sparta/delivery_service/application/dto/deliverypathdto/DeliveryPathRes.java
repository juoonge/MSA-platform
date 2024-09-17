package com.sparta.delivery_service.application.dto.deliverypathdto;

import com.sparta.delivery_service.domain.entity.DeliveryPath;
import com.sparta.delivery_service.domain.enums.DeliveryPathStatus;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 배송 경로 응답 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPathRes {

    private UUID deliveryPathId;

    private UUID deliveryId;

    private UUID hubId;

    private Integer sequenceNumber;

    private BigDecimal estimatedDuration;

    private Double actualDistance;

    private BigDecimal actualDuration;

    private DeliveryPathStatus currentStatus;

    private Integer hubCount;

    public static DeliveryPathRes of(DeliveryPath deliveryPath) {
        return new DeliveryPathRes(
            deliveryPath.getDeliveryPathId(),
            deliveryPath.getDeliveryId(),
            deliveryPath.getHubId(),
            deliveryPath.getSequenceNumber(),
            deliveryPath.getEstimatedDuration(),
            deliveryPath.getActualDistance(),
            deliveryPath.getActualDuration(),
            deliveryPath.getCurrentStatus(),
            deliveryPath.getHubCount()
        );
    }
}