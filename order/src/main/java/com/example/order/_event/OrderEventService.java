package com.example.order._event;

import com.example.order.app.dto.*;
import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import lombok.*;
import org.springframework.kafka.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
public class OrderEventService {

    private final OrderReader orderReader;
    private final KafkaUtils kafkaUtils;

    @Transactional
    @KafkaListener(topics = "delivery-registered", groupId = "order")
    public void orderComplete(String deliverInfo) {
        DeliveryEvent delivery = EventSerializer.deserialize(deliverInfo, DeliveryEvent.class);
        Order order = orderReader.getOrder(delivery.getOrderId());
        order.complete(order.getDeliveryId());
    }

    // todo
    @KafkaListener(topics = "delivery-error", groupId = "order")
    public void deliveryReRegister(String deliveryErrorInfo) {
        DeliveryErrorEvent deliveryError = EventSerializer.deserialize(deliveryErrorInfo, DeliveryErrorEvent.class);
        Order order = orderReader.getOrder(deliveryError.getOrderId());
        kafkaUtils.sendEvent("order-registered", OrderDto.OrderInfo.of(order));
    }
}