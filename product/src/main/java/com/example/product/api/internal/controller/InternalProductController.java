package com.example.product.api.internal.controller;

import com.example.product._common.*;
import com.example.product.api.external.request.*;
import com.example.product.api.internal.response.*;
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
        try {
            return productService.getProduct(productId);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/api/internal/products/{productId}/stock")
    public ChangeStockRes changeStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        try {
            productService.changeStock(productId, request.getAmount());
            return new ChangeStockRes(true);
        } catch (ApiException e) {
            return new ChangeStockRes(false);
        }
    }
}
