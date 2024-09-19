package com.example.product._client.hub.fallback;

import com.example.product._client.hub.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.*;

@Component
public class HubFallbackFactory implements FallbackFactory<HubFeignClient> {
    @Override
    public HubFeignClient create(Throwable cause) {
        return new HubFallback();
    }
}
