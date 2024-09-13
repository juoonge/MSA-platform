package com.example.order._event;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CancelOrderEvent {
    private UUID orderId;
}
