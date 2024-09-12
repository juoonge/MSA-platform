package com.sparta.hub_service.application.service;

import com.sparta.hub_service.application.dto.HubPathDTO;
import com.sparta.hub_service.application.dto.HubPathUpdateResultDTO;
import com.sparta.hub_service.application.mapper.HubPathMapper;
import com.sparta.hub_service.common.exception.ErrorCase;
import com.sparta.hub_service.common.exception.GlobalException;
import com.sparta.hub_service.domain.entity.HubPath;
import com.sparta.hub_service.domain.repository.HubPathRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubPathService {

    private final HubPathRepository hubPathRepository;
    private final HubPathMapper hubPathMapper;

    @Transactional
    public HubPathDTO createHubPath(HubPathDTO hubPathDTO) {
        HubPath hubPath = HubPath.create(hubPathDTO.getStartHubId(), hubPathDTO.getEndHubId());
        hubPath = hubPathRepository.save(hubPath);
        return hubPathMapper.toDto(hubPath);
    }

    public HubPathDTO getHubPath(UUID hubPathId) {
        HubPath hubPath = hubPathRepository.findByHubPathIdAndIsDeletedFalse(hubPathId);
        if (hubPath == null) {
            // 허브 경로를 찾지 못했을 때 ErrorCase를 던집니다.
            throw new GlobalException(ErrorCase.HUB_PATH_NOT_FOUND);
        }
        return hubPathMapper.toDto(hubPath);
    }

    @Transactional(readOnly = true)
    public Page<HubPathDTO> getAllHubPaths(Pageable pageable) {
        return hubPathRepository.findByIsDeletedFalse(pageable).map(hubPathMapper::toDto);
    }

    @Transactional
    public HubPathUpdateResultDTO updateHubPath(UUID hubPathId, HubPathDTO hubPathDTO) {
        HubPath existingHubPath = hubPathRepository.findByHubPathIdAndIsDeletedFalse(hubPathId);
        if (existingHubPath == null) {
            // 허브 경로를 찾지 못했을 때 ErrorCase를 던집니다.
            throw new GlobalException(ErrorCase.HUB_PATH_NOT_FOUND);
        }

        existingHubPath.updateDuration(hubPathDTO.getDuration());
        existingHubPath = hubPathRepository.save(existingHubPath);

        HubPath hubPathCreated = hubPathRepository.save(
            HubPath.create(hubPathDTO.getStartHubId(), hubPathDTO.getEndHubId()));

        return hubPathMapper.toUpdateResultDto(existingHubPath, hubPathCreated);
    }

    @Transactional
    public void deleteHubPath(UUID hubPathId) {
        HubPath existingHubPath = hubPathRepository.findByHubPathIdAndIsDeletedFalse(hubPathId);
        if (existingHubPath == null) {
            // 허브 경로를 찾지 못했을 때 ErrorCase를 던집니다.
            throw new GlobalException(ErrorCase.HUB_PATH_NOT_FOUND);
        }

        existingHubPath.softDeleted();
        hubPathRepository.save(existingHubPath);
    }
}
