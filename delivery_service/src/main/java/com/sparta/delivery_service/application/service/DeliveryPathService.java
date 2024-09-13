package com.sparta.delivery_service.application.service;

import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathCreateReq;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathRes;
import com.sparta.delivery_service.application.dto.deliverypathdto.DeliveryPathUpdateReq;
import com.sparta.delivery_service.common.exception.ErrorCase;
import com.sparta.delivery_service.common.exception.GlobalException;
import com.sparta.delivery_service.domain.entity.DeliveryPath;
import com.sparta.delivery_service.domain.repository.DeliveryPathRepository;
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

    // 배송 경로 생성
    @Transactional
    public DeliveryPathRes createDeliveryPath(DeliveryPathCreateReq request) {
        DeliveryPath deliveryPath = DeliveryPath.createDeliveryPath(request);

        deliveryPathRepository.save(deliveryPath);
        return DeliveryPathRes.of(deliveryPath);
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
