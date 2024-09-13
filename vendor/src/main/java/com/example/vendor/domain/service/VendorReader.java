package com.example.vendor.domain.service;

import com.example.vendor.domain.model.*;
import org.springframework.data.domain.*;

import java.util.*;

public interface VendorReader {
    Vendor getVendor(UUID vendorId);

    List<Vendor> findVendor(Pageable page);
}
