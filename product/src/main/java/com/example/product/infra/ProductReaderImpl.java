package com.example.product.infra;

import com.example.product._common.*;
import com.example.product.domain.model.*;
import com.example.product.domain.repository.*;
import com.example.product.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(UUID productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ApiException("NOT FOUND ENTITY")
        );
    }

    @Override
    public List<Product> findProduct(Pageable page) {
        return productRepository.findAll(page).getContent();
    }
}
