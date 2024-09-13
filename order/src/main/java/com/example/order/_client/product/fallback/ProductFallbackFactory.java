package com.example.order._client.product.fallback;

import com.example.order._client.product.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.*;

@Component
public class ProductFallbackFactory implements FallbackFactory<ProductFeignClient> {
    @Override
    public ProductFeignClient create(Throwable cause) {
        return new ProductFallback();
    }
}
