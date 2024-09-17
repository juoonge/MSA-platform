package com.example.vendor.app.dto;

import com.example.vendor.domain.model.*;
import com.example.vendor.domain.vo.*;
import lombok.*;

import java.util.*;

public class VendorDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class VendorInfo {

        private UUID id;
        private String name;
        private VendorType vendorType;
        private String address;
        private UUID vendorManagerUserId;
        private UUID belongingHubId;

        public static VendorInfo of(Vendor vendor) {
            return VendorInfo.builder()
                    .id(vendor.getId())
                    .name(vendor.getName())
                    .vendorType(vendor.getVendorType())
                    .address(vendor.getAddress())
                    .vendorManagerUserId(vendor.getVendorManagerUserId())
                    .belongingHubId(vendor.getBelongingHubId())
                    .build();
        }
    }

    @Builder
    public static class RegisterVendorCommand {

        private String name;
        private VendorType vendorType;
        private String address;
        private UUID vendorManagerUserId;
        private UUID belongingHubId;

        public Vendor toEntity() {
            return Vendor.builder()
                    .name(this.name)
                    .vendorType(this.vendorType)
                    .address(this.address)
                    .vendorManagerUserId(this.vendorManagerUserId)
                    .belongingHubId(this.belongingHubId)
                    .build();
        }
    }

}
