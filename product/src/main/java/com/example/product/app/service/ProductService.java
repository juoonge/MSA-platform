package com.example.product.app.service;

import com.example.product._client.hub.*;
import com.example.product._client.vendor.*;
import com.example.product._common.*;
import com.example.product.app.dto.ProductDto.*;
import com.example.product.domain.model.*;
import com.example.product.domain.service.*;
import lombok.*;
import org.springframework.dao.*;
import org.springframework.data.domain.*;
import org.springframework.retry.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductReader productReader;
    private final ProductStore productStore;
    private final VendorService vendorService;
    private final HubService hubService;

    @Transactional
    public UUID registerProduct(RegisterProductCommand command) {
        VendorInfo producerVendor = vendorService.getVendor(command.getProducerVendorId());
        if (producerVendor == null) {
            throw new ApiException("VENDOR SERVICE ERROR");
        }
        HubInfo belongingHub = hubService.getHub(command.getBelongingHubId());
        if (belongingHub == null) {
            throw new ApiException("HUB SERVICE ERROR");
        }
        Product intitProduct = command.toEntity();
        Product product = productStore.store(intitProduct);
        return product.getId();
    }

    @Transactional
    public void removeProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        product.remove();
    }

    @Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 50, backoff = @Backoff(delay = 100))
    @Transactional
    public void decreaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProductWithLock(productId);
        if (product.getStock() < amount) {
            throw new ApiException("NOT ENOUGH STOCK");
        }
        product.decreaseStock(amount);
    }

    @Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 50, backoff = @Backoff(delay = 100))
    @Transactional
    public void increaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProductWithLock(productId);
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
        Product product = productReader.getExistProduct(productId);
        return ProductInfo.of(product);
    }
}
