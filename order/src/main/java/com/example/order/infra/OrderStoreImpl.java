package com.example.order.infra;

import com.example.order.domain.model.*;
import com.example.order.domain.repository.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class OrderStoreImpl implements OrderStore {

    private final OrderRepository orderRepository;

    @Override
    public Order store(Order initOrder) {
        return orderRepository.save(initOrder);
    }
}
