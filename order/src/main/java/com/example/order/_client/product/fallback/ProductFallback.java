package com.example.order._client.product.fallback;

import com.example.order._client.product.*;
import com.example.order._client.product.request.*;
import com.example.order._client.product.response.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class ProductFallback implements ProductFeignClient {

    @Override
    public ProductInfo getProduct(UUID productId) {
        return new ProductInfo();
    }

    @Override
    public ChangeStockRes changeStock(UUID productId, ChangeStockReq request) {
        return null;
    }

}
