package com.sparta.hub_service.application.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HubDTO {

    private UUID hubId;  // 허브 ID
    private String name;  // 허브 이름
    private String address;  // 허브 주소
    private BigDecimal latitude;  // 위도
    private BigDecimal longitude;  // 경도
    private Integer hubSequence;  // 허브 순서
    private String hubDeliveryManagerId;  // 허브 배송 담당자 ID
    private List<String> vendorDeliveryManagerIds;  // 업체 배송 담당자 목록
}

