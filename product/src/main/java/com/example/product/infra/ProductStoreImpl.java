package com.example.product.infra;

import com.example.product.domain.model.*;
import com.example.product.domain.repository.*;
import com.example.product.domain.service.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {

    private final ProductRepository productRepository;

    @Override
    public Product store(Product intitProduct) {
        return productRepository.save(intitProduct);
    }
}
