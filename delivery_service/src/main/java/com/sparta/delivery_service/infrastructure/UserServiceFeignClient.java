package com.sparta.delivery_service.infrastructure;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "auth-service")
public interface UserServiceFeignClient {

}

