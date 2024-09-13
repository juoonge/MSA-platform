package com.example.vendor.api.internal.controller;

import com.example.vendor._common.*;
import com.example.vendor.app.dto.*;
import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.app.service.*;
import jakarta.ws.rs.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class InternalVendorController {

    private final VendorService vendorService;

    @GetMapping("/api/internal/vendors/{vendorId}")
    public VendorInfo getVendor(@PathVariable("vendorId") UUID vendorId) {
        try {
            return vendorService.getVendor(vendorId);
        } catch (Exception e) {
            return null;
        }
    }

}
