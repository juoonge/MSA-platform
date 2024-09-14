package com.example.order.domain.model;

import com.example.order._common.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "p_orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID id;
    private UUID orderProductId;
    private Long quantity;
    private LocalDateTime orderedAt;
    private UUID producerVendorId;
    private UUID consumerVendorId;
    private UUID deliveryId;
    @Builder
    public Order(UUID orderProductId, Long quantity, UUID producerVendorId, UUID consumerVendorId, UUID deliveryId) {
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.orderedAt = LocalDateTime.now();
        this.producerVendorId = producerVendorId;
        this.consumerVendorId = consumerVendorId;
        this.deliveryId = deliveryId;
    }
    public void cancel() {
        super.delete();
    }
}
