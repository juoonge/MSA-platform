package com.sparta.delivery_service.application.mapper;

import com.sparta.delivery_service.application.dto.deliverydto.DeliveryDTO;
import com.sparta.delivery_service.domain.entity.Delivery;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {


    DeliveryDTO toDto(Delivery delivery);

}

