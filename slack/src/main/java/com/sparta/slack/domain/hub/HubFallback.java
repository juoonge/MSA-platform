package com.sparta.slack.domain.hub;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class HubFallback implements HubClient{
    private final Throwable cause;
    public HubFallback(final Throwable cause) {this.cause=cause;}

    @Override
    public HubResponseDto getHub(UUID hubId) {
        if (cause instanceof FeignException.NotFound) {
            log.error("Not found error");
        }
        log.error("Failed to get product {}", hubId);
        return null;
    }

}
