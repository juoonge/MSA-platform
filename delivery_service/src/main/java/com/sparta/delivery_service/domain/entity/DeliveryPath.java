package com.sparta.delivery_service.domain.entity;

import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathCreateReq;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathUpdateReq;
import com.sparta.delivery_service.domain.enums.DeliveryPathStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_paths")
public class DeliveryPath extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID deliveryPathId;

    @Column
    private UUID hubId;

    @Column
    private Integer userId;

    @Column
    private Integer sequenceNumber;

    @Column
    private BigDecimal estimatedDuration;

    @Column
    private Double actualDistance;

    @Column
    private BigDecimal actualDuration;

    @Enumerated(EnumType.STRING)
    @Column
    private DeliveryPathStatus currentStatus; // 대기, 이동, 도착

    @Column
    private Integer hubCount;

    // ManyToOne 관계로 Delivery 엔티티와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public static DeliveryPath createDeliveryPath(DeliveryPathCreateReq deliveryPathRes) {
        return DeliveryPath.builder()
            .delivery(deliveryPathRes.getDelivery())  // Delivery 객체를 추가
            .hubId(deliveryPathRes.getHubId())
            .sequenceNumber(deliveryPathRes.getSequenceNumber())
            .estimatedDuration(deliveryPathRes.getEstimatedDuration())
            .actualDistance(deliveryPathRes.getActualDistance())
            .delivery(deliveryPathRes.getDelivery())
            .build();
    }

    public void updateDeliveryPath(DeliveryPathUpdateReq deliveryPathUpdateReq) {
        this.sequenceNumber = deliveryPathUpdateReq.getSequenceNumber();
        this.estimatedDuration = deliveryPathUpdateReq.getEstimatedDuration();
        this.actualDistance = deliveryPathUpdateReq.getActualDistance();
        this.actualDuration = deliveryPathUpdateReq.getActualDuration();
        this.currentStatus = deliveryPathUpdateReq.getCurrentStatus();
        this.hubCount = deliveryPathUpdateReq.getHubCount();
    }

    public void deleteDeliveryPath() {
        setIsDeleted(true);
    }
}
