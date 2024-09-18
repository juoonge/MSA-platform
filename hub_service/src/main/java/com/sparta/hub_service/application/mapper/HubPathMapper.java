package com.sparta.hub_service.application.mapper;

import com.sparta.hub_service.application.dto.HubPathDTO;
import com.sparta.hub_service.application.dto.HubPathUpdateResultDTO;
import com.sparta.hub_service.domain.entity.HubPath;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HubPathMapper {

    @Mapping(source = "startHub.hubId", target = "startHubId")  // startHub 객체에서 hubId 추출
    @Mapping(source = "endHub.hubId", target = "endHubId")  // endHub 객체에서 hubId 추출
    HubPathDTO toDto(HubPath hubpath);  // 엔티티를 DTO로 변환

    @Mapping(target = "startHub.hubId", source = "startHubId")  // DTO의 startHubId를 엔티티의 startHub에 매핑
    @Mapping(target = "endHub.hubId", source = "endHubId")  // DTO의 endHubId를 엔티티의 endHub에 매핑
    HubPath toEntity(HubPathDTO hubPathDTO);  // DTO를 엔티티로 변환 // DTO를 엔티티로 변환

    default HubPathUpdateResultDTO toUpdateResultDto(HubPath updatedHubPath, HubPathDTO createdHubPath) {
        return new HubPathUpdateResultDTO(toDto(updatedHubPath), createdHubPath);
    }
}
