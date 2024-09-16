package com.example.order._client.product;

import com.example.order._client.product.fallback.*;
import com.example.order._client.product.request.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "product-service", fallbackFactory = ProductFallbackFactory.class)
@Primary
public interface ProductFeignClient extends ProductService {

    @GetMapping("/api/internal/products/{productId}")
    ProductInfo getProduct(@PathVariable("productId") UUID productId);

    @PostMapping("/api/internal/products/{productId}/stock/decrease")
    void decreaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeStockReq request);

    @PostMapping("/api/internal/products/{productId}/stock/increase")
    void increaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeStockReq request);

}
