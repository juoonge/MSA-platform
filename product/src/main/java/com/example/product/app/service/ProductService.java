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

    // 캐싱 실패...!
//    @CachePut(cacheNames = "productCache", key = "#result.id")
    @Transactional
    public ProductInfo registerProduct(RegisterProductCommand command) {
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
        return ProductInfo.of(product);
    }

    //    @CacheEvict(cacheNames = "args[0]", allEntries = true)
    @Transactional
    public void removeProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        product.remove();
    }

    //    @CachePut(cacheNames = "productCache", key = "args[0]")
//    @CacheEvict(cacheNames = "productListCache", allEntries = true)
    @Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 50, backoff = @Backoff(delay = 100))
    @Transactional
    public ProductInfo decreaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProductWithLock(productId);
        if (product.getStock() < amount) {
            throw new ApiException("NOT ENOUGH STOCK");
        }
        product.decreaseStock(amount);
        return ProductInfo.of(product);
    }

    //    @CachePut(cacheNames = "productCache", key = "args[0]")
//    @CacheEvict(cacheNames = "productListCache", allEntries = true)
    @Retryable(value = OptimisticLockingFailureException.class, maxAttempts = 50, backoff = @Backoff(delay = 100))
    @Transactional
    public ProductInfo increaseStock(UUID productId, Long amount) {
        Product product = productReader.getExistProductWithLock(productId);
        product.increaseStock(amount);
        return ProductInfo.of(product);
    }

    //    @Cacheable(cacheNames = "productCache", key = "args[0]")
    @Transactional(readOnly = true)
    public ProductInfo retrieveProduct(UUID productId) {
        Product product = productReader.getExistProduct(productId);
        return ProductInfo.of(product);
    }

    //    @Cacheable(
//            cacheNames = "productListCache",
//            key = "{ args[0], args[1].pageNumber, args[1].pageSize }"
//    )
    @Transactional(readOnly = true)
    public List<ProductInfo> retrieveProductList(Pageable page) {
        List<Product> productList = productReader.searchExistProduct(page);
        List<ProductInfo> productInfoList = productList.stream().map(ProductInfo::of).toList();
        return productInfoList;
    }

    //    @Cacheable(cacheNames = "productCache", key = "args[0]")
    @Transactional(readOnly = true)
    public ProductInfo getProduct(UUID productId) {
        Product product = productReader.getExistProduct(productId);
        return ProductInfo.of(product);
    }
}
