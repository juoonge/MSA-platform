package com.example.order._client.delivery;

import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "delivery-service")
@Primary
public interface DeliveryFeignClient extends DeliveryService {
    @PostMapping("/api/internal/deliveries/{deliveryId}/cancel")
    void cancelDelivery(@PathVariable("deliveryId") UUID deliveryId);
}
