package com.example.product.api.internal.controller;

import com.example.product.api.external.request.*;
import com.example.product.app.dto.ProductDto.*;
import com.example.product.app.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class InternalProductController {

    private final ProductService productService;

    @GetMapping("/api/internal/products/{productId}")
    public ProductInfo getProduct(@PathVariable("productId") UUID productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/api/internal/products/{productId}/stock/decrease")
    public void decreaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        productService.decreaseStock(productId, request.getAmount());
    }

    @PostMapping("/api/internal/products/{productId}/stock/increase")
    public void increaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        productService.increaseStock(productId, request.getAmount());
    }
}
