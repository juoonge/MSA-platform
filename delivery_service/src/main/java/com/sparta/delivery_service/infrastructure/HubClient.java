package com.sparta.delivery_service.infrastructure;

import com.sparta.delivery_service.application.dto.hubdto.HubDTO;
import com.sparta.delivery_service.application.dto.hubdto.HubPathDTO;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hub-service")  // 소문자로 수정
public interface HubClient extends HubService{

    @GetMapping("/api/hubs/{hubId}")
    HubDTO getHub(@PathVariable("hubId") UUID hubId);

    @PostMapping("/api/hub-paths")
    HubPathDTO CreateHubPath(@RequestBody HubPathDTO hubPathDTO);
}

