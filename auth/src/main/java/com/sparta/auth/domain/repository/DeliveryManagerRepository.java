package com.sparta.auth.domain.repository;

import com.sparta.auth.domain.model.DeliveryManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryManagerRepository extends JpaRepository<DeliveryManager, UUID> {
    Page<DeliveryManager> findByNameStartingWithOrContactStartingWith(String keyword, String keyword1, PageRequest pageRequest);
}
