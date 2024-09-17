package com.example.order._client.product.fallback;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._common.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ProductFallback implements ProductFeignClient {

    @Override
    public ProductInfo getProduct(UUID productId) {
        return new ProductInfo();
    }

    @Override
    public void decreaseStock(UUID productId, ChangeStockReq request) {
        throw new ApiException("PRODUCT SERVICE ERROR");
    }

    @Override
    public void increaseStock(UUID productId, ChangeStockReq request) {
        throw new ApiException("PRODUCT SERVICE ERROR");
    }

}
