package com.example.product._client.vendor;

import org.springframework.stereotype.*;

import java.util.*;

@Component
public class VendorFallback implements VendorFeignClient {

    @Override
    public Boolean exists(UUID vendorId) {
        return null;
    }

}
