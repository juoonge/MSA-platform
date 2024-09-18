package com.sparta.hub_service.application.service;

import static com.sparta.hub_service.common.exception.ErrorCase.HUB_NOT_FOUND;

import com.sparta.hub_service.application.dto.HubPathDTO;
import com.sparta.hub_service.application.dto.HubPathUpdateResultDTO;
import com.sparta.hub_service.application.mapper.HubPathMapper;
import com.sparta.hub_service.common.exception.ErrorCase;
import com.sparta.hub_service.common.exception.GlobalException;
import com.sparta.hub_service.config.GeminiApiClientConfig;
import com.sparta.hub_service.domain.entity.Hub;
import com.sparta.hub_service.domain.entity.HubPath;
import com.sparta.hub_service.domain.repository.HubPathRepository;
import com.sparta.hub_service.domain.repository.HubRepository;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HubPathService {

    private final HubPathRepository hubPathRepository;
    private final HubPathMapper hubPathMapper;
    private final GeminiApiClientConfig geminiApiClientConfig;
    private final HubRepository hubRepository;  // 허브 정보를 조회하기 위한 Repository 추가

    private static final String INSTRUCTION = "답변은 소요시간 BigDecimal에 바로 입력할 수 있게 간략하게 숫자만 임의의 값을 넣어주고 조언 안 해줘도 됩니다.";

    // AI API에서 상품 설명을 생성하고 소요 시간을 숫자로 반환하는 메서드
    public String generateProductDescription(String prompt) {
        // 요청 텍스트에 추가 지시사항 삽입
        String fullPrompt = prompt + " " + INSTRUCTION;

        // API 호출을 통해 상품 설명 생성
        String estimatedDurationStr = geminiApiClientConfig.generateContent(fullPrompt);

        // 숫자 추출 (정규 표현식 사용)
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(estimatedDurationStr);
        if (matcher.find()) {
            estimatedDurationStr = matcher.group();
        } else {
            // 숫자를 찾지 못한 경우 처리 (예: 로그 기록, 기본값 설정)
            log.error("Failed to extract number from Gemini API response: {}",
                estimatedDurationStr);
            estimatedDurationStr = "0"; // 기본값 설정
        }

        return estimatedDurationStr; // 숫자만 반환
    }

    public Hub calculateEndHub(Hub startHub, Integer sequenceNumber) {
        Hub endHub = null;
        Integer hubSequence = startHub.getHubSequence();
        if (sequenceNumber > 0) {
            endHub = hubRepository.findByHubSequenceAndIsDeletedFalse(hubSequence + 1)
                .orElseThrow(() -> new GlobalException(HUB_NOT_FOUND));
        } else if (sequenceNumber < 0) {
            endHub = hubRepository.findByHubSequenceAndIsDeletedFalse(hubSequence - 1)
                .orElseThrow(() -> new GlobalException(HUB_NOT_FOUND));
        }

        return endHub;
    }

    @Transactional
    public HubPathDTO createHubPath(HubPathDTO hubPathDTO) {
        Integer sequenceNumber = hubPathDTO.getSequenceNumber();
        if (sequenceNumber == 0) {
            return null;
        }

        Hub startHub = hubRepository.findById(hubPathDTO.getStartHubId())
            .orElseThrow(() -> new RuntimeException("시작 허브를 찾을 수 없습니다."));
        Hub endHub = calculateEndHub(startHub, sequenceNumber);

        // 위도와 경도를 기반으로 소요 시간을 추측하기 위한 프롬프트 생성
        String prompt = String.format(
            "시작 허브의 위도 (%s)와 경도 (%s) 및 종료 허브의 위도 (%s)와 경도 (%s)를 기준으로 " +
                "이 두 위치 간의 이동 소요 시간을 추측해 주세요.",
            startHub.getLatitude(), startHub.getLongitude(),
            endHub.getLatitude(), endHub.getLongitude()
        );

        // AI API를 호출하여 소요 시간 추측
        String estimatedDurationStr = generateProductDescription(prompt);

        // BigDecimal로 변환 및 예외 처리
        BigDecimal estimatedDuration;
        try {
            estimatedDuration = new BigDecimal(estimatedDurationStr);
        } catch (NumberFormatException e) {
            log.error("Failed to convert to BigDecimal: {}", estimatedDurationStr, e);
            estimatedDuration = BigDecimal.ZERO; // 기본값 설정
        }

        // HubPath 생성 및 저장
        HubPath hubPath = HubPath.create(startHub, endHub);
        hubPath.updateDuration(estimatedDuration);
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

    /*
     * 배송 경로 엔티티가 0이면 호출 x
     */
    @Transactional
    public HubPathUpdateResultDTO updateHubPath(UUID hubPathId, HubPathDTO hubPathDTO) {
        HubPath existingHubPath = hubPathRepository.findByHubPathIdAndIsDeletedFalse(hubPathId);
        if (existingHubPath == null) {
            // 허브 경로를 찾지 못했을 때 ErrorCase를 던집니다.
            throw new GlobalException(ErrorCase.HUB_PATH_NOT_FOUND);
        }

        existingHubPath.updateDuration(hubPathDTO.getDuration());
        existingHubPath = hubPathRepository.save(existingHubPath);
        if (hubPathDTO.getStartHubId() == null || hubPathDTO.getEndHubId() == null) {
            return hubPathMapper.toUpdateResultDto(existingHubPath, null);
        }
        HubPathDTO hubPathCreated = createHubPath(hubPathDTO);

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
