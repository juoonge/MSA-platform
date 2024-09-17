package com.example.order._client.delivery.fallback;

import com.example.order._client.delivery.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.*;

@Component
public class DeliveryFallbackFactory implements FallbackFactory<DeliveryFeignClient> {
    @Override
    public DeliveryFeignClient create(Throwable cause) {
        return new DeliveryFallback();
    }
}
