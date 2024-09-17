package com.example.product.app.service;

import com.example.product._client.vendor.*;
import com.example.product._common.*;
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
    private final VendorService vendorService;

    @Transactional
    public ProductInfo registerProduct(RegisterProductCommand command) {
//        vendorService.getVendor(command.getProducerVendorId());
        // hub 존재 확인
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
    public void decreaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProduct(productId);
        if (product.getStock() < amount) {
            throw new ApiException("NOT ENOUGH STOCK");
        }
        product.decreaseStock(amount);
    }

    @Transactional
    public void increaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProduct(productId);
        product.increaseStock(amount);
    }

    @Transactional(readOnly = true)
    public ProductInfo retrieveProduct(UUID productId) {
        Product product = productReader.getExistProduct(productId);
        return ProductInfo.of(product);
    }

    @Transactional(readOnly = true)
    public List<ProductInfo> retrieveProductList(Pageable page) {
        List<Product> productList = productReader.searchExistProduct(page);
        List<ProductInfo> productInfoList = productList.stream().map(ProductInfo::of).toList();
        return productInfoList;
    }

    @Transactional(readOnly = true)
    public ProductInfo getProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        return ProductInfo.of(product);
    }
}
