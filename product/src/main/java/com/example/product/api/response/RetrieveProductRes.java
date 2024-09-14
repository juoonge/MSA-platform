package com.example.product.api.response;

import com.example.product.app.dto.ProductDto.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@Builder
public class RetrieveProductRes implements Serializable {

    private UUID id;
    private String name;
    private Long stock;
    private UUID producerVendorId;
    private UUID belongingHubId;

    public static RetrieveProductRes of(ProductInfo info) {
        return RetrieveProductRes.builder()
                .id(info.getId())
                .name(info.getName())
                .stock(info.getStock())
                .producerVendorId(info.getProducerVendorId())
                .belongingHubId(info.getBelongingHubId())
                .build();
    }
}
