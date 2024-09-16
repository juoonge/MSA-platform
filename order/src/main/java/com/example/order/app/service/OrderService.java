package com.example.order.app.service;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._client.product.response.*;
import com.example.order._client.vendor.*;
import com.example.order._common.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderReader orderReader;
    private final OrderStore orderStore;
    private final ProductService productService;
    private final VendorService vendorService;

    @Transactional
    public OrderInfo registerOrder(RegisterOrderCommand command) {
        VendorInfo consumerVendor = getVendor(command.getConsumerVendorId());
        ProductInfo orderProduct = getProduct(command.getOrderProductId());
        changeStock(orderProduct, command.getQuantity());
        Order order = storeOrder(orderProduct.getId(), command.getQuantity(), orderProduct.getProducerVendorId(), consumerVendor.getId());
        return OrderInfo.of(order);
    }

    private ProductInfo getProduct(UUID productId) {
        ProductInfo product = productService.getProduct(productId);
        if (product == null) {
            throw new ApiException("NOT FOUND PRODUCT");
        } else if (product.getId() == null) {
            throw new ApiException("PRODUCT SERVICE ERROR");
        }
        return product;
    }

    private VendorInfo getVendor(UUID vendorId) {
        VendorInfo vendor = vendorService.getVendor(vendorId);
        if (vendor == null) {
            throw new ApiException("NOT FOUND VENDOR");
        } else if (vendor.getId() == null) {
            throw new ApiException("VENDOR SERVICE ERROR");
        }
        return vendor;
    }

    private void changeStock(ProductInfo orderProduct, Long amount) {
        if (orderProduct.getStock() < amount) {
            throw new ApiException("NOT ENOUGH STOCK");
        }
        ChangeStockRes res = productService.changeStock(orderProduct.getId(), new ChangeStockReq(amount));
        if (!res.getResult()) {
            throw new ApiException("NOT ENOUGH STOCK");
        } else if (res.getResult() == null) {
            throw new ApiException("PRODUCT SERVICE ERROR");
        }
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

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderReader.getOrder(orderId);
        order.cancel();
    }

    @Transactional(readOnly = true)
    public OrderInfo retrieveOrder(UUID orderId) {
        Order order = orderReader.getOrder(orderId);
        return OrderInfo.of(order);
    }

    @Transactional(readOnly = true)
    public List<OrderInfo> retrieveOrderList(Pageable page) {
        List<Order> orderList = orderReader.searchOrder(page);
        List<OrderInfo> orderInfoList = orderList.stream().map(OrderInfo::of).toList();
        return orderInfoList;
    }
}
