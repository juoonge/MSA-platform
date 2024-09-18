package com.sparta.hub_service.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "delivery-service")
public interface DeliveryClient extends DeliveryService {

}
