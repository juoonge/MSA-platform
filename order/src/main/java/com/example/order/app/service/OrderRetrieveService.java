package com.example.order.app.service;

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
public class OrderRetrieveService {

    private final OrderReader orderReader;

    @Transactional(readOnly = true)
    public OrderInfo retrieveOrder(UUID orderId) {
        Order order = orderReader.getExistOrder(orderId);
        return OrderInfo.of(order);
    }

    @Transactional(readOnly = true)
    public List<OrderInfo> retrieveOrderList(Pageable page) {
        List<Order> orderList = orderReader.searchExistOrder(page);
        List<OrderInfo> orderInfoList = orderList.stream().map(OrderInfo::of).toList();
        return orderInfoList;
    }
}
