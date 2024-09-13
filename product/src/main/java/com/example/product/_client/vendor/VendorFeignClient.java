package com.example.product._client.vendor;

import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "vendor-service" , fallbackFactory = VendorFallbackFactory.class)
@Primary
public interface VendorFeignClient extends VendorService {

    @GetMapping("/api/internal/vendors/{vendorId}")
    Boolean exists(@PathVariable("vendorId") UUID vendorId);

}
