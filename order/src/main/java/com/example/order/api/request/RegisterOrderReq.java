package com.example.order.api.request;

import com.example.order.app.dto.*;
import com.example.order.app.dto.OrderDto.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Getter
@NoArgsConstructor
public class RegisterOrderReq {

    private UUID orderProductId;
    private Long quantity;
    private UUID consumerVendorId;

    public RegisterOrderCommand toCommand() {
        return RegisterOrderCommand.builder()
                .orderProductId(this.orderProductId)
                .quantity(this.quantity)
                .consumerVendorId(this.consumerVendorId)
                .build();
    }
}
