package com.example.vendor.domain.model;

import com.example.vendor._common.*;
import com.example.vendor.domain.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "p_vendors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vendor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vendor_id")
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private VendorType vendorType;
    private String address;
    private UUID vendorManagerUserId;
    private UUID belongingHubId;

    @Builder
    public Vendor(String name, VendorType vendorType, String address, UUID vendorManagerUserId, UUID belongingHubId) {
        this.name = name;
        this.vendorType = vendorType;
        this.address = address;
        this.vendorManagerUserId = vendorManagerUserId;
        this.belongingHubId = belongingHubId;
    }

    public void changeBelongingHub(UUID belongingHubId) {
        this.belongingHubId = belongingHubId;
    }
}
