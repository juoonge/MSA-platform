package com.example.product.domain.service;

import com.example.product.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface ProductReader {
    Product getProduct(UUID productId);

    Product getExistProduct(UUID orderId);

    Product getExistProductWithLock(UUID productId);

    List<Product> searchProduct(Pageable page);

    List<Product> searchExistProduct(Pageable page);
}
