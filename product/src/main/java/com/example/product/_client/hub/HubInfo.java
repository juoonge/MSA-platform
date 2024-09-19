package com.example.product._client.hub;

import lombok.*;

import java.math.*;
import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HubInfo {
    private UUID hubId;
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer hubSequence;
    private String hubDeliveryManagerId;
    private List<String> vendorDeliveryManagerIds;
}
