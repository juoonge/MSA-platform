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

    @Transactional
    public void changeBelongingHub(UUID vendorId, UUID belongingHubId) {
        Vendor vendor = vendorReader.getVendor(vendorId);
        vendor.changeBelongingHub(belongingHubId);
    }

    @Transactional(readOnly = true)
    public VendorInfo retrieveVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getVendor(vendorId);
        return VendorInfo.of(vendor);
    }

    @Transactional(readOnly = true)
    public List<VendorInfo> retrieveVendorList(Pageable page) {
        List<Vendor> vendorList = vendorReader.findVendor(page);
        List<VendorInfo> vendorInfoList = vendorList.stream().map(VendorInfo::of).toList();
        return vendorInfoList;
    }

    @Transactional
    public VendorInfo getVendor(UUID vendorId) {
        Vendor vendor = vendorReader.getVendor(vendorId);
        return VendorInfo.of(vendor);
    }
}
