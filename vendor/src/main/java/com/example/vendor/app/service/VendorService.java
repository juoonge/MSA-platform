package com.example.vendor.app.service;

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
    public UUID registerVendor(RegisterVendorCommand command) {
        // 유저 검증
        // 허브 검증
        Vendor initVendor = command.toEntity();
        Vendor vendor = vendorStore.store(initVendor);
        return vendor.getId();
    }

    @Transactional
    public void removeVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getVendor(vendorId);
        vendor.remove();
    }

    @Transactional(readOnly = true)
    public VendorInfo retrieveVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getExistVendor(vendorId);
        return VendorInfo.of(vendor);
    }

    @Transactional(readOnly = true)
    public List<VendorInfo> retrieveVendorList(Pageable page) {
        List<Vendor> vendorList = vendorReader.searchExistVendor(page);
        List<VendorInfo> vendorInfoList = vendorList.stream().map(VendorInfo::of).toList();
        return vendorInfoList;
    }

    @Transactional(readOnly = true)
    public VendorInfo getVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getExistVendor(vendorId);
        return VendorInfo.of(vendor);
    }
}
