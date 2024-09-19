package com.example.product._event;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCancelEvent {
    private UUID orderId;
    private UUID orderProductId;
    private Long quantity;
    private UUID producerVendorId;
    private UUID consumerVendorId;
    private UUID deliveryId;
}
