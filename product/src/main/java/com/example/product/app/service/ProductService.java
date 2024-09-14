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
    private final VendorFeignClient vendorFeignClient;

    @Transactional
    public ProductInfo registerProduct(RegisterProductCommand command) {
        Boolean existsVendor = vendorFeignClient.exists(command.getProducerVendorId());
        if (existsVendor == null) {
            throw new ApiException("VENDOR SERVICE ERROR");
        } else if (!existsVendor) {
            throw new ApiException("NOT FOUND VENDOR");
        }
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

    @Transactional(readOnly = true)
    public ProductInfo retrieveProduct(UUID productId) {
        Product product = productReader.getProduct(productId);
        return ProductInfo.of(product);
    }

    @Transactional(readOnly = true)
    public List<ProductInfo> retrieveProductList(Pageable page) {
        List<Product> productList = productReader.findProduct(page);
        List<ProductInfo> productInfoList = productList.stream().map(ProductInfo::of).toList();
        return productInfoList;
    }

}
