package com.example.vendor.api.external.response;

import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.domain.vo.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveVendorRes implements Serializable {

    private UUID id;
    private String name;
    private VendorType vendorType;
    private String address;
    private UUID vendorManagerUserId;
    private UUID belongingHubId;

    public static RetrieveVendorRes of(VendorInfo info) {
        return RetrieveVendorRes.builder()
                .id(info.getId())
                .name(info.getName())
                .vendorType(info.getVendorType())
                .address(info.getAddress())
                .vendorManagerUserId(info.getVendorManagerUserId())
                .belongingHubId(info.getBelongingHubId())
                .build();
    }
}
