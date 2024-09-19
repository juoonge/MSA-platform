package com.example.product.app.dto;

import com.example.product.domain.model.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @Getter
    @Builder
    public static class RegisterProductCommand {

        private String name;
        private Long stock;
        private UUID producerVendorId;
        private UUID belongingHubId;

        public Product toEntity() {
            return Product.builder()
                    .name(this.name)
                    .stock(this.stock)
                    .producerVendorId(this.producerVendorId)
                    .belongingHubId(this.belongingHubId)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductInfo implements Serializable {

        private UUID id;
        private String name;
        private Long stock;
        private UUID producerVendorId;
        private UUID belongingHubId;

        public static ProductInfo of(Product product) {
            return ProductInfo.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .stock(product.getStock())
                    .producerVendorId(product.getProducerVendorId())
                    .belongingHubId(product.getBelongingHubId())
                    .build();
        }
    }

}
