package com.example.vendor.api.external.controller;

import com.example.vendor._common.*;
import com.example.vendor.api.external.request.*;
import com.example.vendor.api.external.response.*;
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

    @DeleteMapping("/api/vendors/{vendorId}")
    public ApiResponse withdrawVendor(@PathVariable("vendorId") UUID vendorId) {
        vendorService.withdrawVendor(vendorId);
        return ApiResponse.success("업체 탈퇴", null);
    }

    @PatchMapping("/api/vendors/{vendorId}")
    public ApiResponse changeBelongingHub(@PathVariable("vendorId") UUID vendorId, @RequestBody ChangeBelongingHubReq request) {
        vendorService.changeBelongingHub(vendorId, request.getBelongingHubId());
        return ApiResponse.success("소속 허브 변경", null);
    }

    @GetMapping("/api/vendors/{vendorId}")
    public ApiResponse<RetrieveVendorRes> retrieveVendor(@PathVariable("vendorId") UUID vendorId) {
        VendorInfo info = vendorService.retrieveVendor(vendorId);
        return ApiResponse.success("업체 단건 조회", RetrieveVendorRes.of(info));
    }

    @GetMapping("/api/vendors")
    public ApiResponse<List<RetrieveVendorRes>> retrieveVendorList(Pageable page) {
        List<VendorInfo> infoList = vendorService.retrieveVendorList(page);
        List<RetrieveVendorRes> res = infoList.stream().map(RetrieveVendorRes::of).toList();
        return ApiResponse.success("업체 단건 조회", res);
    }
}
