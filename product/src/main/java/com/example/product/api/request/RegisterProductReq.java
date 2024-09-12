package com.example.product.api.request;

import com.example.product.app.dto.*;
import com.example.product.app.dto.ProductDto.*;
import lombok.*;

import java.util.*;

@Getter
@NoArgsConstructor
public class RegisterProductReq {

    private String name;
    private Long stock;
    private UUID producerVendorId;
    private UUID belongingHubId;

    public RegisterProductCommand toCommand() {
        return RegisterProductCommand.builder()
                .name(this.name)
                .stock(this.stock)
                .producerVendorId(this.producerVendorId)
                .belongingHubId(this.belongingHubId)
                .build();
    }
}
