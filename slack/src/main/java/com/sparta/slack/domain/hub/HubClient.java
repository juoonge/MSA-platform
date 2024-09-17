package com.sparta.slack.domain.hub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name="hub",
        fallbackFactory=HubFallbackFactory.class
)

public interface HubClient extends HubService{
    @GetMapping("hubs/internal/{hubId}")
    HubResponseDto getHub(@PathVariable("hubId")UUID hubId);
}
