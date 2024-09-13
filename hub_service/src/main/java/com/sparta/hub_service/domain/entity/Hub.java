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

    @Column(nullable = false)
    private String name;  // 허브 이름

    @Column(nullable = false)
    private String address;  // 허브 주소

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal latitude;  // 위도

    @Column(precision = 12, scale = 8, nullable = false)
    private BigDecimal longitude;  // 경도

    @Column(name = "hub_sequence", nullable = false)
    private Integer hubSequence;  // 허브 순서


    public void updateHub(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void deleteHub() {
        this.setIsDeleted(true);
    }

//    @Column(name = "hub_delivery_manager_id")
//    private String hubDeliveryManagerId;  // 허브 배송 담당자 ID
//
//    @ElementCollection
//    @CollectionTable(name = "vendor_delivery_managers", joinColumns = @JoinColumn(name = "hub_id"))
//    @Column(name = "vendor_delivery_manager_id")
//    private List<String> vendorDeliveryManagerIds = new ArrayList<>();  // 업체 배송 담당자 목록

}
