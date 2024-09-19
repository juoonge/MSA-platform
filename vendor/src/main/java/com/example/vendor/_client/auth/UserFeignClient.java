package com.example.vendor._client.auth;

import com.example.vendor._client.auth.fallback.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient(name = "auth-service", fallbackFactory = UserFallbackFactory.class)
@Primary
public interface UserFeignClient extends UserService {

    @GetMapping("/api/internal/auth/{user_id}")
    UserInfo getUser(@PathVariable UUID user_id);

}
