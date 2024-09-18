package com.sparta.delivery_service.application.dto.deliverydto;

import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import lombok.Getter;

@Getter
public class DeliveryStatusUpdateRequest {

    private DeliveryStatus deliveryStatus;
}

