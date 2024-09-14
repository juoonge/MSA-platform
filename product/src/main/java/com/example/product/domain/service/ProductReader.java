package com.example.product.domain.service;

import com.example.product.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface ProductReader {
    Product getProduct(UUID productId);

    List<Product> findProduct(Pageable page);
}
