package com.sparta.hub_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "p_hub_paths")
public class HubPath extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hub_path_id")
    private UUID hubPathId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_hub_id", referencedColumnName = "hub_id", nullable = false)
    private Hub startHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_hub_id", referencedColumnName = "hub_id", nullable = false)
    private Hub endHub;

    @Column(name = "duration")
    private BigDecimal duration;  // 소요 시간

    public static HubPath create(final Hub startHub, final Hub endHub) {
        HubPath hubPath = HubPath.builder()
            .startHub(startHub)
            .endHub(endHub)
            .build();
        hubPath.setIsDeleted(false);
        return hubPath;
    }

    public void updateDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public void softDeleted() {
        this.setIsDeleted(true);
    }
}
