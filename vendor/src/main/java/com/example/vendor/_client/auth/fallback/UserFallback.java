package com.example.vendor._client.auth.fallback;

import com.example.vendor._client.auth.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class UserFallback implements UserFeignClient {

    @Override
    public UserInfo getUser(UUID userId) {
        return new UserInfo();
    }

}
