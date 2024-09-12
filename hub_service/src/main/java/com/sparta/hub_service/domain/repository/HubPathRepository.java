package com.sparta.hub_service.domain.repository;

import com.sparta.hub_service.domain.entity.HubPath;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubPathRepository extends JpaRepository<HubPath, UUID> {

    HubPath findByHubPathIdAndIsDeletedFalse(UUID hubPathId);

    Page<HubPath> findByIsDeletedFalse(Pageable pageable);  // 페이징 및 논리적 삭제 처리
}

