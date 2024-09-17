package com.sparta.hub_service.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "p_hubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hub extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hub_id")
    private UUID hubId;  // 허브 ID

    @Column
    private String name;  // 허브 이름

    @Column
    private String address;  // 허브 주소

    @Column(precision = 11, scale = 8)
    private BigDecimal latitude;  // 위도

    @Column(precision = 12, scale = 8)
    private BigDecimal longitude;  // 경도

    @Column(name = "hub_sequence", unique = true)
    private Integer hubSequence;  // 허브 순서

    // 시작 허브와의 연관 관계
    @OneToMany(mappedBy = "startHub", fetch = FetchType.LAZY)
    private List<HubPath> hubPathsFrom;

    // 종료 허브와의 연관 관계
    @OneToMany(mappedBy = "endHub", fetch = FetchType.LAZY)
    private List<HubPath> hubPathsTo;

    public void updateHub(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void deleteHub() {
        this.setIsDeleted(true);
    }
}
