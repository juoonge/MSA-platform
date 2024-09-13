package com.example.order.api.response;

import com.example.order.app.dto.*;
import lombok.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RetrieveOrderRes implements Serializable {

    private UUID orderId;
    private UUID orderProductId;
    private Long quantity;
    private LocalDateTime orderedAt;
    private UUID producerVendorId;
    private UUID consumerVendorId;
    private UUID deliveryId;

    public static RetrieveOrderRes of(OrderDto.OrderInfo info) {
        return RetrieveOrderRes.builder()
                .orderId(info.getId())
                .orderProductId(info.getOrderProductId())
                .quantity(info.getQuantity())
                .orderedAt(info.getOrderedAt())
                .producerVendorId(info.getProducerVendorId())
                .consumerVendorId(info.getConsumerVendorId())
                .deliveryId(info.getDeliveryId())
                .build();
    }

}
