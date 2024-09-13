package com.example.order._client.product;

import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
    private UUID id;
    private String name;
    private Long stock;
    private UUID producerVendorId;
    private UUID belongingHubId;
}
