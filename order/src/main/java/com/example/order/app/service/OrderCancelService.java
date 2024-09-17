package com.example.order.app.service;

import com.example.order._client.delivery.*;
import com.example.order._common.*;
import com.example.order.domain.model.*;
import com.example.order.domain.service.*;
import feign.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderCancelService {

    private final OrderReader orderReader;
    private final DeliveryService deliveryService;

    @Transactional
    public void cancelOrder(UUID orderId) {
        Order order = orderReader.getExistOrder(orderId);
//        cancelDelivery(order.getDeliveryId()); // todo
        order.cancel();
    }

    private void cancelDelivery(UUID deliveryId) {
        try {
            deliveryService.cancelDelivery(deliveryId);
        } catch (FeignException e) {
            throw new ApiException("FAIL CANCEL DELIVERY");
        }
    }
}
