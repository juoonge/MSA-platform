package com.sparta.delivery_service.application.service;

import com.sparta.delivery_service.application.dto.deliverydto.DeliveryAddressDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryDTO;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryStatusDto;
import com.sparta.delivery_service.application.dto.deliverydto.DeliveryUpdateRequest;
import com.sparta.delivery_service.application.mapper.DeliveryMapper;
import com.sparta.delivery_service.common.exception.ErrorCase;
import com.sparta.delivery_service.common.exception.GlobalException;
import com.sparta.delivery_service.domain.entity.Delivery;
import com.sparta.delivery_service.domain.enums.DeliveryStatus;
import com.sparta.delivery_service.domain.repository.DeliveryRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public Delivery createDelivery(DeliveryDTO deliveryDto) {
        // 배송 생성 로직
        Delivery delivery = Delivery.createDelivery(deliveryDto);

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public DeliveryDTO updateDelivery(UUID deliveryId, DeliveryUpdateRequest request) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDelivery(request);

        Delivery updatedDelivery = deliveryRepository.save(delivery);
        return deliveryMapper.toDto(updatedDelivery);

    }

    public DeliveryStatusDto updateDeliveryStatus(UUID deliveryId, DeliveryStatus deliveryStatus) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDeliveryStatus(deliveryStatus);
        Delivery updatedDelivery = deliveryRepository.save(delivery);

        return DeliveryStatusDto.of(updatedDelivery.getDeliveryId(),
            updatedDelivery.getDeliveryStatus());
    }

    public DeliveryAddressDto updateDeliveryAddress(UUID deliveryId, String address) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.updateDeliveryAddress(address);
        Delivery updatedDelivery = deliveryRepository.save(delivery);

        return DeliveryAddressDto.of(updatedDelivery);
    }

    @Transactional
    public void deleteDelivery(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));

        delivery.deleteDelivery();
        deliveryRepository.save(delivery);
    }

    @Transactional(readOnly = true)
    public Delivery getDeliveryById(UUID deliveryId) {
        return deliveryRepository.findByDeliveryIdAndIsDeletedFalse(deliveryId)
            .orElseThrow(() -> new GlobalException(ErrorCase.NOT_FOUND));
    }

    public Page<Delivery> getAllDeliveries(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

}

