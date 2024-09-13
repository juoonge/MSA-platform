package com.example.product._client.vendor;

import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface VendorService {
    Boolean exists(UUID vendorId);

}
