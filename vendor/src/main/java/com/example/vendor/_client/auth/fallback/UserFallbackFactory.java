package com.example.vendor._client.auth.fallback;

import com.example.vendor._client.auth.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.stereotype.*;

@Component
public class UserFallbackFactory implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFallback();
    }
}
