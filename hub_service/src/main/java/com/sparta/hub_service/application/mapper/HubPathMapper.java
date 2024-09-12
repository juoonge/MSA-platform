package com.sparta.hub_service.application.mapper;

import com.sparta.hub_service.application.dto.HubPathDTO;
import com.sparta.hub_service.application.dto.HubPathUpdateResultDTO;
import com.sparta.hub_service.domain.entity.HubPath;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HubPathMapper {

    HubPathDTO toDto(HubPath hubpath);  // 엔티티를 DTO로 변환

    HubPath toEntity(HubPathDTO hubPathDTO);  // DTO를 엔티티로 변환

    default HubPathUpdateResultDTO toUpdateResultDto(HubPath updatedHubPath, HubPath createdHubPath) {
        return new HubPathUpdateResultDTO(toDto(updatedHubPath), toDto(createdHubPath));
    }
}
