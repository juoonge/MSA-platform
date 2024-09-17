package com.example.order._event;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryEvent {
    private UUID deliveryId;
    private UUID orderId;
}
