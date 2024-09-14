package com.example.vendor.infra;

import com.example.vendor.domain.model.*;
import com.example.vendor.domain.repository.*;
import com.example.vendor.domain.service.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class VendorStoreImpl implements VendorStore {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor store(Vendor initVendor) {
        return vendorRepository.save(initVendor);
    }
}
