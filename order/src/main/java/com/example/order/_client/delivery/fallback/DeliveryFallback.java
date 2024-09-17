package com.example.order._client.delivery.fallback;

import com.example.order._client.delivery.*;
import com.example.order._common.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class DeliveryFallback implements DeliveryFeignClient {

    @Override
    public void cancelDelivery(UUID deliveryId) {
        new ApiException("DELIVERY SERVICE ERROR");
    }
}
