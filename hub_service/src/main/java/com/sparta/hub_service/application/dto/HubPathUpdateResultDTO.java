package com.sparta.hub_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HubPathUpdateResultDTO {

    private HubPathDTO updatedHubPath;  // 수정된 HubPath 정보
    private HubPathDTO createdHubPath;  // 새로 생성된 HubPath 정보
}

