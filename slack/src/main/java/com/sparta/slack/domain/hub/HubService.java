package com.sparta.slack.domain.hub;

import java.util.UUID;

public interface HubService {
    HubResponseDto getHub(UUID hubId);
}
