package com.example.product.api.controller;

import com.example.product._common.*;
import com.example.product.api.request.*;
import com.example.product.api.response.*;
import com.example.product.app.dto.ProductDto.*;
import com.example.product.app.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/products")
    public ApiResponse<RegisterProductRes> registerProduct(@RequestBody RegisterProductReq request) {
        System.out.println(request);
        ProductInfo productInfo = productService.registerProduct(request.toCommand());
        return ApiResponse.success("상품 등록", RegisterProductRes.of(productInfo));
    }

    @DeleteMapping("/api/products/{productId}")
    public ApiResponse removeProduct(@PathVariable("productId") UUID productId) {
        productService.removeProduct(productId);
        return ApiResponse.success("상품 삭제", null);
    }

    @PatchMapping("/api/products/{productId}")
    public ApiResponse changeProductStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        productService.changeProductStock(productId, request.getAmount());
        return ApiResponse.success("상품 재고 변경", null);
    }

}
