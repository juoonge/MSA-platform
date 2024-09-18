package com.sparta.hub_service.domain.repository;

import com.sparta.hub_service.domain.entity.Hub;
import com.sparta.hub_service.domain.entity.HubPath;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HubPathRepository extends JpaRepository<HubPath, UUID> {

    HubPath findByHubPathIdAndIsDeletedFalse(UUID hubPathId);

    Page<HubPath> findByIsDeletedFalse(Pageable pageable);  // 페이징 및 논리적 삭제 처리

    @Query("SELECT hp FROM HubPath hp WHERE hp.startHub = :startHub AND hp.endHub = :endHub")
    Optional<HubPath> findByStartHubAndEndHub(@Param("startHub") Hub startHub, @Param("endHub") Hub endHub);

}

