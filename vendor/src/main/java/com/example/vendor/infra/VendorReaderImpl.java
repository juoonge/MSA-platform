package com.example.vendor.infra;

import com.example.vendor._common.*;
import com.example.vendor.domain.model.*;
import com.example.vendor.domain.repository.*;
import com.example.vendor.domain.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class VendorReaderImpl implements VendorReader {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor getVendor(UUID vendorId) {
        return vendorRepository.findById(vendorId).orElseThrow(
                () -> new ApiException("NOT FOUND VENDOR")
        );
    }

    @Override
    public Vendor getExistVendor(UUID vendorId) {
        return vendorRepository.findByIdAndIsDeleted(vendorId, false).orElseThrow(
                () -> new ApiException("NOT FOUND ORDER")
        );
    }

    @Override
    public List<Vendor> searchVendor(Pageable page) {
        return vendorRepository.findAll(page).getContent();
    }

    @Override
    public List<Vendor> searchExistVendor(Pageable page) {
        return vendorRepository.findAllByIsDeleted(page, false).getContent();
    }

}
