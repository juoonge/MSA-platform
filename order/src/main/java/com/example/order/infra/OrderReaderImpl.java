package com.example.order.infra;

import com.example.order._common.*;
import com.example.order.app.dto.*;
import com.example.order.app.dto.OrderDto.*;
import com.example.order.domain.model.*;
import com.example.order.domain.repository.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class OrderReaderImpl implements OrderReader {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ApiException("NOT FOUND ENTITY")
        );
    }

    @Override
    public List<Order> searchOrder(Pageable page) {
        return orderRepository.findAll(page).getContent();
    }
}
