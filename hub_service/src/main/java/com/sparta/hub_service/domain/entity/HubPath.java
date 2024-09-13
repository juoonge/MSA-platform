package com.sparta.hub_service.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "start_hub_id", nullable = false)
    private Long startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private Long endHubId;

    @Column(name = "duration")
    private BigDecimal duration;  // 소요 시간

    @Builder
    public static HubPath create(final Long startHubId, final Long endHubId) {
        HubPath hubPath = HubPath.builder()
            .startHubId(startHubId)
            .endHubId(endHubId)
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

