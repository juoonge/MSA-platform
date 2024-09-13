package com.example.vendor.api.internal.controller;

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
    public Boolean exists(@PathVariable("vendorId") UUID vendorId) {
        return vendorService.exists(vendorId);
    }

}
