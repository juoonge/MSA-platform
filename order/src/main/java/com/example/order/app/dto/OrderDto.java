package com.example.order.app.dto;

import com.example.order.domain.model.*;
import lombok.*;

import java.time.*;
import java.util.*;

public class OrderDto {

    @Builder
    public static class RegisterOrderCommand {

        private UUID orderProductId;
        private Long quantity;
        private UUID producerVendorId;
        private UUID consumerVendorId;
        private UUID deliveryId;

        public Order toEntity() {
            return Order.builder()
                    .orderProductId(this.orderProductId)
                    .quantity(this.quantity)
                    .producerVendorId(this.producerVendorId)
                    .consumerVendorId(this.consumerVendorId)
                    .deliveryId(this.deliveryId)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class OrderInfo {
        private UUID id;
        private UUID orderProductId;
        private Long quantity;
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
