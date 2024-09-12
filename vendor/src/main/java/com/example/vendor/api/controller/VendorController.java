package com.example.vendor.api.controller;

import com.example.vendor._common.*;
import com.example.vendor.api.request.*;
import com.example.vendor.api.response.*;
import com.example.vendor.app.dto.VendorDto.*;
import com.example.vendor.app.service.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping("/api/vendors")
    public ApiResponse<RegisterVendorRes> registerVendor(@RequestBody RegisterVendorReq request) {
        VendorInfo info = vendorService.registerVendor(request.toCommand());
        return ApiResponse.success("업체 등록", new RegisterVendorRes(info.getId()));
    }


}
