package com.sparta.delivery_service.application.dto.deliverypathdto;

import com.sparta.delivery_service.domain.enums.DeliveryPathStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 배송 경로 수정 요청 DTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPathUpdateReq {

    private Integer sequenceNumber;

    private Integer estimatedDuration;

    private Double actualDistance;

    private Integer actualDuration;

    private DeliveryPathStatus currentStatus;

    private Integer hubCount;
}
