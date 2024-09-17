package com.example.order.domain.model;

import com.example.order._common.*;
import com.example.order.domain.vo.*;
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
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private UUID orderProductId;
    private Long quantity;
    private LocalDateTime orderedAt;
    private UUID producerVendorId;
    private UUID consumerVendorId;
    private UUID deliveryId;

    @Builder
    public Order(UUID orderProductId, Long quantity, UUID producerVendorId, UUID consumerVendorId) {
        this.orderStatus = OrderStatus.PENDING;
        this.orderProductId = orderProductId;
        this.quantity = quantity;
        this.orderedAt = LocalDateTime.now();
        this.producerVendorId = producerVendorId;
        this.consumerVendorId = consumerVendorId;
    }

    public void addDelivery(UUID deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void complete(UUID deliveryId) {
        this.addDelivery(deliveryId);
        this.orderStatus = OrderStatus.COMPLETE;
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }

    public void remove() {
        super.delete();
    }
}
