package com.sparta.delivery_service.application.service;

import com.sparta.delivery_service.application.dto.deliverydto.DeliveryAddressDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryDTO;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryStatusDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryUpdateRequest;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathCreateReq;
import com.sparta.delivery_service.application.dto.hubdto.HubDTO;
import com.sparta.delivery_service.application.mapper.DeliveryMapper;
import com.sparta.delivery_service.common.exception.ErrorCase;
import com.sparta.delivery_service.common.exception.GlobalException;
import com.sparta.delivery_service.config.GeminiApiClientConfig;
import com.sparta.delivery_service.domain.entity.Delivery;
import com.sparta.delivery_service.domain.entity.DeliveryPath;
import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import com.sparta.delivery_service.domain.repository.DeliveryRepository;
import com.sparta.delivery_service.infrastructure.HubService;
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
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final HubService hubService;
    private final DeliveryPathService deliveryPathService;
    private final GeminiApiClientConfig geminiApiClientConfig;

    private static final String INSTRUCTION = "답변은 Double로 사용하게 숫자 하나만, 두 사이의 거리 대략적으로 알려줘 조언 안해줘도 돼";

    // AI API에서 상품 설명을 생성하고 소요 시간을 숫자로 반환하는 메서드
    public String generateProductDescription(String prompt) {
        // 요청 텍스트에 추가 지시사항 삽입
        String fullPrompt = prompt + " " + INSTRUCTION;

        // API 호출을 통해 응답 내용 생성
        String estimatedDistanceStr = geminiApiClientConfig.generateContent(fullPrompt);

        // 숫자 추출 (정규 표현식 사용)
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(estimatedDistanceStr);
        if (matcher.find()) {
            estimatedDistanceStr = matcher.group();
        } else {
            // 숫자를 찾지 못한 경우 처리 (예: 로그 기록, 기본값 설정)
            log.error("Failed to extract number from Gemini API response: {}", estimatedDistanceStr);
            estimatedDistanceStr = "0"; // 기본값 설정
        }

        return estimatedDistanceStr; // 숫자만 반환
    }


    /*
    *
    * 주문 측에서 생성
     */
    public Delivery createDelivery(DeliveryDTO deliveryDto) {
        // startHubId와 endHubId로 허브 정보 조회
        HubDTO startHub = hubService.getHub(deliveryDto.getStartHubId());
        HubDTO endHub = hubService.getHub(deliveryDto.getEndHubId());

        // 두 허브 사이의 거리 계산을 위한 프롬프트 생성
        String prompt = String.format("두 허브 사이의 거리를 계산해 주세요: %s 와 %s",
            startHub.getAddress(), endHub.getAddress());

        // AI API 호출을 통해 거리 계산
        String actualDistanceStr = generateProductDescription(prompt);
        Double actualDistance = Double.parseDouble(actualDistanceStr);

        // 배송 경로 생성
        Delivery delivery = Delivery.createDelivery(deliveryDto);
        Delivery savedDelivery = deliveryRepository.save(delivery);
        DeliveryPathCreateReq deliveryPathCreateReq = DeliveryPathCreateReq.builder()
            .deliveryId(savedDelivery.getDeliveryId())
            .delivery(savedDelivery)
            .hubId(startHub.getHubId())
            .sequenceNumber(endHub.getHubSequence() - startHub.getHubSequence())
            .actualDistance(actualDistance)  // AI로 계산된 거리 적용
            .build();

        deliveryPathService.createDeliveryPath(deliveryPathCreateReq);

        return deliveryRepository.save(savedDelivery);
    }


    /*
    * 주문 측에서 수정?
     */
    @Transactional
    public DeliveryDTO updateDelivery(UUID deliveryId, DeliveryUpdateRequest request) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDelivery(request);

        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return deliveryMapper.toDto(updatedDelivery);

    }
    /*
    * @return update DeliveryStatus
    * 배송원이 직접 설정함
    *
     */
    public DeliveryStatusDto updateDeliveryStatus(UUID deliveryId, DeliveryStatus deliveryStatus) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDeliveryStatus(deliveryStatus);
        Delivery updatedDelivery = deliveryRepository.save(delivery);

        return DeliveryStatusDto.of(updatedDelivery.getDeliveryId(),
            updatedDelivery.getDeliveryStatus());
    }
    /*
    *
    * 주문 측에서 수정함
     */
    public DeliveryAddressDto updateDeliveryAddress(UUID deliveryId, String address) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDeliveryAddress(address);
        Delivery updatedDelivery = deliveryRepository.save(delivery);

        return DeliveryAddressDto.of(updatedDelivery);
    }

    /*
    *
    * 주문 측에서 삭제함
     */
    @Transactional
    public Delivery deleteDelivery(UUID deliveryId) {
        // Delivery 찾기
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        // 관련된 DeliveryPath 삭제 처리
        delivery.getDeliveryPathList().forEach(DeliveryPath::deleteDeliveryPath);

        // Delivery 삭제 처리
        delivery.deleteDelivery();
        return deliveryRepository.save(delivery);
    }

    /*
    *
    * 마스터와 배송원 자신의 배달 확인
     */
    @Transactional(readOnly = true)
    public Delivery getDeliveryById(UUID deliveryId) {
        return deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));
    }

    /*
    * 마스터만 조회 가능
     */
    public Page<Delivery> getAllDeliveries(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

}

