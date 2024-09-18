package com.sparta.delivery_service.application.service;

import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathCreateReq;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathRes;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathUpdateReq;
import com.sparta.delivery_service.application.dto.hubdto.HubPathDTO;
import com.sparta.delivery_service.common.exception.ErrorCase;
import com.sparta.delivery_service.common.exception.GlobalException;
import com.sparta.delivery_service.domain.entity.DeliveryPath;
import com.sparta.delivery_service.domain.repository.DeliveryPathRepository;
import com.sparta.delivery_service.infrastructure.HubService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryPathService {

    private final DeliveryPathRepository deliveryPathRepository;
    private final HubService hubService;
    // 배송 경로 생성
    @Transactional
    public DeliveryPathRes createDeliveryPath(DeliveryPathCreateReq request) {
        DeliveryPath deliveryPath = DeliveryPath.createDeliveryPath(request);

        HubPathDTO hubPathDTO = new HubPathDTO(deliveryPath.getSequenceNumber(), request.getHubId());
        HubPathDTO createdHubPathDTO = hubService.CreateHubPath(hubPathDTO);

        deliveryPath.setEstimatedDuration(createdHubPathDTO.getDuration());

        deliveryPathRepository.save(deliveryPath);
        return DeliveryPathRes.of(deliveryPath);
    }

    public DeliveryPathRes arriveAtHubAndUpdateDeliveryPath(Integer userId) {

        // 기존 허브 배송원으로 배송경로 찾기

        // SequenceNumber가 0 인지 체크하고 0이면 delivery의 배송상태를 변경한다.
        // deliveryPath에 actualDuration 갱신후 리턴

        //sequenceNumber가 0이 아니라면 -1 or +1 후 hub_path create을 함
        //deliverypath 기존 actualDuration 갱신 후 createDeliveryPath로 생성
        //return 기존 deliveryPath와 createDeliveryPath를 함
        return null;
    }
    // 단일 배송 경로 조회
    @Transactional(readOnly = true)
    public DeliveryPathRes getDeliveryPath(UUID deliveryPathId) {
        DeliveryPath deliveryPath = deliveryPathRepository.findByDeliveryPathIdAndIsDeletedFalse(
                deliveryPathId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));
        return DeliveryPathRes.of(deliveryPath);
    }

    // 배송 경로 목록 조회
    @Transactional(readOnly = true)
    public Page<DeliveryPath> getAllDeliveryPaths(Pageable pageable) {
        return deliveryPathRepository.findAllActiveDeliveryPaths(pageable);
    }

    // 배송 경로 수정
    @Transactional
    public DeliveryPathRes updateDeliveryPath(UUID deliveryPathId, DeliveryPathUpdateReq request) {
        DeliveryPath deliveryPath = deliveryPathRepository.findByDeliveryPathIdAndIsDeletedFalse(
                deliveryPathId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        deliveryPath.updateDeliveryPath(request);

        return DeliveryPathRes.of(deliveryPath);
    }

    /*
     * 배송 경로 삭제
     */
    @Transactional
    public void deleteDeliveryPath(UUID deliveryPathId) {
        DeliveryPath deliveryPath = deliveryPathRepository.findByDeliveryPathIdAndIsDeletedFalse(
                deliveryPathId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        deliveryPath.deleteDeliveryPath();
        deliveryPathRepository.save(deliveryPath);
    }
}
