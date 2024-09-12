package com.example.product.app.service;

import com.example.product.app.dto.ProductDto.*;
import com.example.product.domain.model.*;
import com.example.product.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductReader productReader;
    private final ProductStore productStore;

    @Transactional
    public ProductInfo registerProduct(RegisterProductCommand command) {
        Product intitProduct = command.toEntity();
        Product product = productStore.store(intitProduct);
        return ProductInfo.of(product);
    }

    @Transactional
    public void removeProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        product.remove();
    }

    @Transactional
    public void changeProductStock(UUID productId, Long amount) {
        Product product = productReader.getProduct(productId);
        product.changeStock(amount);
    }

}
