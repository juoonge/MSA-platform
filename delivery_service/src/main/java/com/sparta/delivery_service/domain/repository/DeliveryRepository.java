package com.sparta.delivery_service.domain.repository;

import com.sparta.delivery_service.domain.entity.Delivery;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    //Page<Delivery> findAllByUserId(@NonNull UUID userId,@NonNull Pageable pageable);

    @NonNull
    Page<Delivery> findAll(@NonNull Pageable pageable);

    Optional<Delivery> findByDeliveryIdAndIsDeletedFalse(@NonNull UUID deliveryId);
}

