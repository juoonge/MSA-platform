package com.example.vendor.api.internal.controller;

import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.app.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class InternalVendorController {

    private final VendorService vendorService;

    @GetMapping("/api/internal/vendors/{vendorId}")
    public VendorInfo getVendor(@PathVariable("vendorId") UUID vendorId) {
        return vendorService.getVendor(vendorId);
    }
}
