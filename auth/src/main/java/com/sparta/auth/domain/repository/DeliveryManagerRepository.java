package com.sparta.auth.domain.repository;

import com.sparta.auth.domain.model.DeliveryManager;
import com.sparta.auth.domain.model.DeliveryManagerRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryManagerRepository extends JpaRepository<DeliveryManager, UUID> {
    Page<DeliveryManager> findByRoleStartingWith(DeliveryManagerRole role, Pageable pageable);
}
