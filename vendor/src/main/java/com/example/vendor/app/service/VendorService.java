package com.example.vendor.app.service;

import com.example.vendor._client.auth.UserInfo;
import com.example.vendor._client.auth.UserService;
import com.example.vendor._client.hub.HubInfo;
import com.example.vendor._client.hub.HubService;
import com.example.vendor._common.ApiException;
import com.example.vendor.app.dto.VendorDto.RegisterVendorCommand;
import com.example.vendor.app.dto.VendorDto.VendorInfo;
import com.example.vendor.domain.model.Vendor;
import com.example.vendor.domain.service.VendorReader;
import com.example.vendor.domain.service.VendorStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VendorService {

    private final VendorReader vendorReader;
    private final VendorStore vendorStore;
    private final UserService userService;
    private final HubService hubService;

    @Transactional
    public UUID registerVendor(RegisterVendorCommand command) {
        UserInfo vendorManager = userService.getUser(command.getVendorManagerUserId());
        if (vendorManager == null) { // todo vendorId null
            throw new ApiException("AUTH SERVICE ERROR");
        }
        HubInfo belongingHub = hubService.getHub(command.getBelongingHubId());
        if (belongingHub == null) {
            throw new ApiException("HUB SERVICE ERROR");
        }
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
