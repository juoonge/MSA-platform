package com.example.vendor.app.service;

import com.example.vendor.app.dto.*;
import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.domain.model.*;
import com.example.vendor.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorReader vendorReader;
    private final VendorStore vendorStore;

    @Transactional
    public VendorInfo registerVendor(RegisterVendorCommand command) {
        Vendor initVendor = command.toEntity();
        Vendor vendor = vendorStore.store(initVendor);
        return VendorInfo.of(vendor);
    }

    @Transactional
    public void withdrawVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getVendor(vendorId);
        vendor.withdraw();
    }

}
