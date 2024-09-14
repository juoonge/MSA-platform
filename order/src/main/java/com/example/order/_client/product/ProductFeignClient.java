package com.example.order._client.product;

import com.example.order._client.product.fallback.*;
import com.example.order._client.product.request.*;
import com.example.order._client.product.response.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "product-service", url = "http://localhost:19200", fallbackFactory = ProductFallbackFactory.class)
@Primary
public interface ProductFeignClient extends ProductService {

    @GetMapping("/api/internal/products/{productId}")
    ProductInfo getProduct(@PathVariable("productId") UUID productId);

    @PatchMapping("/api/internal/products/{productId}/stock")
    ChangeStockRes changeStock(@PathVariable("productId") UUID productId, @RequestBody ChangeStockReq request);

}
