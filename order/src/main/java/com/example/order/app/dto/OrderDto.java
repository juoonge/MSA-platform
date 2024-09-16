package com.example.order.app.dto;

import com.example.order.domain.model.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.*;
import java.time.*;
import java.util.*;

public class OrderDto {

    @Getter
    @Builder
    public static class RegisterOrderCommand {

        private UUID orderProductId;
        private Long quantity;
        private UUID consumerVendorId;

        public Order toEntity() {
            return Order.builder()
                    .orderProductId(this.orderProductId)
                    .quantity(this.quantity)
                    .consumerVendorId(this.consumerVendorId)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderInfo implements Serializable {
        private UUID id;
        private UUID orderProductId;
        private Long quantity;
        @JsonIgnore
        private LocalDateTime orderedAt;
        private UUID producerVendorId;
        private UUID consumerVendorId;
        private UUID deliveryId;

        public static OrderInfo of(Order order) {
            return OrderInfo.builder()
                    .id(order.getId())
                    .orderProductId(order.getOrderProductId())
                    .quantity(order.getQuantity())
                    .orderedAt(order.getOrderedAt())
                    .producerVendorId(order.getProducerVendorId())
                    .consumerVendorId(order.getConsumerVendorId())
                    .deliveryId(order.getDeliveryId())
                    .build();
        }
    }

}
