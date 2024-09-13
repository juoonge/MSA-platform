package com.example.product._client.vendor;

import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.*;

@Component
public class VendorFallbackFactory implements FallbackFactory<VendorFeignClient> {
    @Override
    public VendorFeignClient create(Throwable cause) {
        return new VendorFallback();
    }
}
