package com.example.order.app.service;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._client.vendor.*;
import com.example.order._common.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import feign.*;
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
        decreaseProductStock(orderProduct, command.getQuantity());
        Order order = storeOrder(orderProduct.getId(), command.getQuantity(), orderProduct.getProducerVendorId(), consumerVendor.getId());
        return OrderInfo.of(order);
    }

    private ProductInfo getProduct(UUID productId) {
        ProductInfo product;
        try {
            product = productService.getProduct(productId);
        } catch (FeignException e) {
            throw new ApiException("FAIL GET VENDOR");
        }
        return product;
    }

    private VendorInfo getVendor(UUID vendorId) {
        VendorInfo vendor;
        try {
            vendor = vendorService.getVendor(vendorId);
        } catch (FeignException e) {
            throw new ApiException("FAIL GET VENDOR");
        }
        return vendor;
    }

    private void decreaseProductStock(ProductInfo orderProduct, Long amount) {
        try {
            productService.decreaseStock(orderProduct.getId(), new ChangeStockReq(amount));
        } catch (FeignException e) {
            throw new ApiException("FAIL DECREASE PRODUCT STOCK");
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
