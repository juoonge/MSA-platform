package com.example.vendor._client.hub;

import com.example.vendor._client.hub.fallback.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "hub-service", fallbackFactory = HubFallbackFactory.class)
@Primary
public interface HubFeignClient extends HubService {

    @GetMapping("/api/internal/hubs/{hubId}")
    HubInfo getHub(@PathVariable("hubId") UUID hubId);

}
