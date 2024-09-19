package com.example.product._client.hub.fallback;

import com.example.product._client.hub.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class HubFallback implements HubFeignClient {

    @Override
    public HubInfo getHub(UUID hubId) {
        return new HubInfo();
    }

}
