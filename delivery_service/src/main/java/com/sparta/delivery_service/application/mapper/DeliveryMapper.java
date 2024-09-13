package com.sparta.delivery_service.application.mapper;

import com.sparta.delivery_service.application.dto.DeliveryCreateRequest;
import com.sparta.delivery_service.application.dto.DeliveryDTO;
import com.sparta.delivery_service.application.dto.DeliveryUpdateRequest;
import com.sparta.delivery_service.domain.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryDTO toDto(Delivery delivery);

    Delivery toEntity(DeliveryCreateRequest request);

    Delivery toEntity(DeliveryUpdateRequest request);
}

