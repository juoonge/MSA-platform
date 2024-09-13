package com.example.vendor.api.external.response;

import com.example.vendor.app.dto.VendorDto.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVendorRes implements Serializable {

    private UUID vendorId;

}
