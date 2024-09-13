package com.example.product._client;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorInfo {
    private UUID id;
    private String name;
    private String vendorType;
    private String address;
    private UUID vendorManagerUserId;
    private UUID belongingHubId;
}
