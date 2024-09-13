package com.example.product._client.fallback;

import com.example.product._client.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class VendorFallback implements VendorFeignClient {

    @Override
    public VendorInfo getVendor(UUID vendorId) {
        return null;
    }

}
