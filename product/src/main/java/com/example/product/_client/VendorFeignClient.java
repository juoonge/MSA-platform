package com.example.product._client;

import com.example.product._client.fallback.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "vendor-service" , fallbackFactory = VendorFallbackFactory.class)
@Primary
public interface VendorFeignClient extends VendorService {

    @GetMapping("/api/internal/vendors/{vendorId}")
    VendorInfo getVendor(@PathVariable("vendorId") UUID vendorId);

}
