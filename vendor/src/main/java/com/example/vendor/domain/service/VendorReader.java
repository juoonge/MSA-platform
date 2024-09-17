package com.example.vendor.domain.service;

import com.example.vendor.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface VendorReader {
    Vendor getVendor(UUID vendorId);

    Vendor getExistVendor(UUID vendorId);

    List<Vendor> searchVendor(Pageable page);

    List<Vendor> searchExistVendor(Pageable page);
}
