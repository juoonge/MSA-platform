package com.example.vendor.domain.repository;

import com.example.vendor.domain.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByIdAndIsDeleted(UUID vendorId, boolean b);

    Page<Vendor> findAllByIsDeleted(Pageable page, boolean b);
}
