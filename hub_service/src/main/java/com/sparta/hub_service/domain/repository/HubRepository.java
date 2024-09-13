package com.sparta.hub_service.domain.repository;

import com.sparta.hub_service.domain.entity.Hub;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface HubRepository extends JpaRepository<Hub, UUID> {

    Optional<Hub> findByHubIdAndIsDeletedFalse(UUID HubId);

    List<Hub> findAllByIsDeletedFalse();
    // 필요한 커스텀 쿼리 메소드 작성 가능
}
