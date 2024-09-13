package com.example.order._client.product;

import com.example.order._client.product.request.*;
import com.example.order._client.product.response.*;

import java.util.*;

public interface ProductService {
    ProductInfo getProduct(UUID productId);

    ChangeStockRes changeStock(UUID productId, ChangeStockReq request);
}
