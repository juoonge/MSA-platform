package com.example.order.app.service;

import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderRemoveService {

    private final OrderReader orderReader;

    @Transactional
    public void removeOrder(UUID orderId) {
        Order order = orderReader.getOrder(orderId);
        order.remove();
    }

}
