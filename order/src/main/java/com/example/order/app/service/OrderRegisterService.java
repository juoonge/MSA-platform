package com.example.order.app.service;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._client.vendor.*;
import com.example.order._common.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderRegisterService {

    private final OrderStore orderStore;
    private final ProductService productService;
    private final VendorService vendorService;

    @Transactional
    public UUID registerOrder(RegisterOrderCommand command) {
        VendorInfo consumerVendor = vendorService.getVendor(command.getConsumerVendorId());
        ProductInfo orderProduct = productService.getProduct(command.getOrderProductId());
        productService.decreaseStock(orderProduct.getId(), new ChangeStockReq(command.getQuantity()));
        Order order;
        try {
            order = storeOrder(orderProduct.getId(), command.getQuantity(), orderProduct.getProducerVendorId(), consumerVendor.getId());
        } catch (Exception e) {
            throw new OrderRegisterException(command.getOrderProductId(), command.getQuantity(), "ERROR AFTER DECREASE STOCK");
        }
        return order.getId();
    }

    private Order storeOrder(UUID orderProductId, Long quantity, UUID producerVendorId, UUID consumerVendorId) {
        Order initOrder = Order.builder()
                .orderProductId(orderProductId)
                .quantity(quantity)
                .producerVendorId(producerVendorId)
                .consumerVendorId(consumerVendorId)
                .build();
        return orderStore.store(initOrder);
    }

}
