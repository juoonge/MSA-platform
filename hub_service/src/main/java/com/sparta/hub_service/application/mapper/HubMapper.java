package com.sparta.hub_service.application.mapper;

import com.sparta.hub_service.application.dto.HubDTO;
import com.sparta.hub_service.domain.entity.Hub;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HubMapper {

    HubDTO toDto(Hub hub);  // 엔티티를 DTO로 변환

    Hub toEntity(HubDTO hubDTO);  // DTO를 엔티티로 변환

}
