package com.sparta.delivery_service.domain.entity;

import com.sparta.delivery_service.application.dto.deliverydto.DeliveryDTO;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryUpdateRequest;
import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliveries")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends TimeStamped implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID deliveryId;

    @Column
    private UUID orderId;

    @Column
    private UUID vendorId;

    @Column
    private UUID startHubId;

    @Column
    private UUID endHubId;

    @Enumerated(EnumType.STRING)
    @Column
    private DeliveryStatus deliveryStatus;

    @Column
    private String address;

    public static Delivery createDelivery(DeliveryDTO deliveryDto) {
        return Delivery.builder()
            .orderId(deliveryDto.getOrderId())
            .vendorId(deliveryDto.getVendorId())
            .startHubId(deliveryDto.getStartHubId())
            .endHubId(deliveryDto.getEndHubId())
            .deliveryStatus(DeliveryStatus.PENDING)
            .address(deliveryDto.getAddress())
            .build();
    }

    public void updateDelivery(DeliveryUpdateRequest request) {
        this.address = request.getAddress();
        this.startHubId = request.getStartHubId();
        this.endHubId = request.getEndHubId();
    }

    public void updateDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void updateDeliveryAddress(String address) {
        this.address = address;
    }

    public void deleteDelivery() {
        setIsDeleted(true);
    }
}

