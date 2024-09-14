package com.example.order._client.vendor;

import com.example.order._client.vendor.fallback.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "vendor-service", url = "http://localhost:19100", fallbackFactory = VendorFallbackFactory.class)
@Primary
public interface VendorFeignClient extends VendorService {

    @GetMapping("/api/internal/vendors/{vendorId}")
    VendorInfo getVendor(@PathVariable("vendorId") UUID vendorId);

}
