package com.sparta.hub_service.application.service;

import com.sparta.hub_service.application.dto.HubDTO;
import com.sparta.hub_service.application.mapper.HubMapper;
import com.sparta.hub_service.common.exception.ErrorCase;
import com.sparta.hub_service.common.exception.GlobalException;
import com.sparta.hub_service.domain.entity.Hub;
import com.sparta.hub_service.domain.repository.HubRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubRepository hubRepository;
    private final HubMapper hubMapper;

    /**
     * 허브 생성
     */
    public UUID createHub(HubDTO hubDTO) {
        Hub hub = hubMapper.toEntity(hubDTO);  // DTO를 엔티티로 변환
        Hub savedHub = hubRepository.save(hub);  // 허브 저장
        return savedHub.getHubId();  // 생성된 허브 ID 반환
    }

    /**
     * 허브 단건 조회
     */
    public HubDTO getHub(UUID hubId) {
        Hub hub = hubRepository.findByHubIdAndIsDeletedFalse(hubId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));  // 허브가 없으면 예외 발생

        return hubMapper.toDto(hub);  // 엔티티를 DTO로 변환
    }

    /**
     * 허브 목록 조회
     */
    public List<HubDTO> getAllHubs() {
        List<Hub> hubs = hubRepository.findAllByIsDeletedFalse();  // 모든 허브 조회
        return hubs.stream()
            .map(hubMapper::toDto)  // 각 허브 엔티티를 DTO로 변환
            .collect(Collectors.toList());
    }

    /**
     * 허브 수정
     * existingHub.setHubDeliveryManagerId(hubDTO.getHubDeliveryManagerId());
     * existingHub.setVendorDeliveryManagerIds(hubDTO.getVendorDeliveryManagerIds()); 추가하기
     */
    public void updateHub(UUID hubId, HubDTO hubDTO) {
        Hub existingHub = hubRepository.findByHubIdAndIsDeletedFalse(hubId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));  // 허브가 없으면 예외 발생

        // 수정된 정보로 허브 업데이트
        existingHub.updateHub(hubDTO.getName(), hubDTO.getAddress());

        hubRepository.save(existingHub);  // 변경 사항 저장
    }

    /**
     * 허브 삭제
     */
    public void deleteHub(UUID hubId) {
        Hub hub = hubRepository.findByHubIdAndIsDeletedFalse(hubId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));  // 허브가 없으면 예외 발생

        // 허브 삭제
        hub.deleteHub();
    }
}
