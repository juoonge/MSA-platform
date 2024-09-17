package com.example.product.app.service;

import com.example.product.app.dto.ProductDto.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;
import java.util.concurrent.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void decrease_with_100_request() throws InterruptedException {
        // given
        RegisterProductCommand command = RegisterProductCommand.builder()
                .name("McBook16")
                .stock(10000L)
                .belongingHubId(UUID.fromString("45da4c48-6456-437e-b954-cfcbbfba465e"))
                .producerVendorId(UUID.fromString("45da4c48-6456-437e-b954-cfcbbfba465e"))
                .build();
        UUID productId = productService.registerProduct(command);

        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch latch = new CountDownLatch(threadCount);

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    productService.decreaseStock(productId, 5L);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        // then
        ProductInfo product = productService.getProduct(productId);
        Assertions.assertEquals(5000L, product.getStock());
    }
}