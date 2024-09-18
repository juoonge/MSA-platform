package com.example.order._client.vendor.fallback;

import com.example.order._client.vendor.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class VendorFallback implements VendorFeignClient {

    @Override
    public VendorInfo getVendor(UUID vendorId) {
        return new VendorInfo();
    }

}
