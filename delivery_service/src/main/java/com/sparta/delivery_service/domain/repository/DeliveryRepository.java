package com.sparta.delivery_service.domain.repository;

import com.sparta.delivery_service.domain.entity.Delivery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

    //Page<Delivery> findAllByUserId(@NonNull UUID userId,@NonNull Pageable pageable);

    @NonNull
    Page<Delivery> findAll(@NonNull Pageable pageable);

    Optional<Delivery> findByDeliveryIdAndIsDeletedFalse(@NonNull UUID deliveryId);

    // 허브별로 지난 24시간 이내에 진행된 주문을 조회하는 메서드
    @Query("SELECT d FROM Delivery d WHERE d.startHubId = :hubId AND d.createdAt >= :startTime AND d.createdAt <= :endTime")
    List<Delivery> findAllByHubIdAndTimeRange(@Param("hubId") UUID hubId,
        @Param("startTime") LocalDateTime startTime,
        @Param("endTime") LocalDateTime endTime);

    Optional<Delivery> findAllByUserId(UUID userId);

    List<Delivery> findAll();
}

