package com.example.order.app.service;

import com.example.order.app.dto.*;
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

    @Transactional
    public OrderInfo registerOrder(RegisterOrderCommand command) {
        Order initOrder = command.toEntity();
        Order order = orderStore.store(initOrder);
        return OrderInfo.of(order);
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
