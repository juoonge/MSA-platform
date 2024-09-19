package com.example.product.api.external.controller;

import com.example.product._common.*;
import com.example.product.api.external.request.*;
import com.example.product.api.external.response.*;
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
        ProductInfo productInfo = productService.registerProduct(request.toCommand());
        return ApiResponse.success("상품 등록", new RegisterProductRes(productInfo.getId()));
    }

    @DeleteMapping("/api/products/{productId}")
    public ApiResponse removeProduct(@PathVariable("productId") UUID productId) {
        productService.removeProduct(productId);
        return ApiResponse.success("상품 삭제");
    }

    @PatchMapping("/api/products/{productId}/stock/decrease")
    public ApiResponse decreaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        productService.decreaseStock(productId, request.getAmount());
        return ApiResponse.success("상품 재고 감소");
    }

    @PatchMapping("/api/products/{productId}/stock/increase")
    public ApiResponse increaseStock(@PathVariable("productId") UUID productId, @RequestBody ChangeProductStockReq request) {
        productService.increaseStock(productId, request.getAmount());
        return ApiResponse.success("상품 재고 증가");
    }

    @GetMapping("/api/products/{productId}")
    public ApiResponse<RetrieveProductRes> retrieveProduct(@PathVariable("productId") UUID productId) {
        ProductInfo info = productService.retrieveProduct(productId);
        return ApiResponse.success("상품 단건 조회", RetrieveProductRes.of(info));
    }

    @GetMapping("/api/products")
    public ApiResponse<List<RetrieveProductRes>> retrieveProductList(Pageable page) {
        List<ProductInfo> productInfoList = productService.retrieveProductList(page);
        List<RetrieveProductRes> res = productInfoList.stream().map(RetrieveProductRes::of).toList();
        return ApiResponse.success("상품 목록 조회", res);
    }

}
