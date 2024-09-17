package com.example.vendor.api.external.request;

import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.domain.vo.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@NoArgsConstructor
public class RegisterVendorReq {

    private String name;
    private VendorType vendorType;
    private String address;
    private UUID vendorManagerUserId;
    private UUID belongingHubId;

    public RegisterVendorCommand toCommand() {
        return RegisterVendorCommand.builder()
                .name(this.name)
                .vendorType(this.vendorType)
                .address(this.address)
                .vendorManagerUserId(this.vendorManagerUserId)
                .belongingHubId(this.belongingHubId)
                .build();
    }
}
