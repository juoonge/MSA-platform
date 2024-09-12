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

}
