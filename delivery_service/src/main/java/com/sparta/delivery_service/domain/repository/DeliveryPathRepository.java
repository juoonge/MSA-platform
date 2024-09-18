package com.sparta.delivery_service.domain.repository;

import com.sparta.delivery_service.domain.entity.DeliveryPath;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPathRepository extends JpaRepository<DeliveryPath, UUID> {

    @Query("SELECT dp FROM DeliveryPath dp WHERE dp.isDeleted = false")
    Page<DeliveryPath> findAllActiveDeliveryPaths(Pageable pageable);

    Optional<DeliveryPath> findByDeliveryPathIdAndIsDeletedFalse(UUID deliveryPathId);
}

