package com.example.product._event;

import com.example.product.app.service.*;
import lombok.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class ProductEventService {

    private final ProductService productService;

    @Transactional
    @KafkaListener(topics = "order-cancel", groupId = "product")
    public void orderComplete(String orderCancelEvent) {
        OrderCancelEvent event = EventSerializer.deserialize(orderCancelEvent, OrderCancelEvent.class);
        productService.increaseStock(event.getOrderProductId(), event.getQuantity());
    }

}
