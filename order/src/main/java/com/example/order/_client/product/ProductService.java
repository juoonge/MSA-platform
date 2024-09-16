package com.example.order._client.product;

import com.example.order._client.product.request.*;

import java.util.*;

public interface ProductService {
    ProductInfo getProduct(UUID productId);

    void decreaseStock(UUID productId, ChangeStockReq request);

    void increaseStock(UUID productId, ChangeStockReq request);
}
