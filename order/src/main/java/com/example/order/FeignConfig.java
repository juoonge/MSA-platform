package com.example.order;

import feign.okhttp.*;
import org.springframework.context.annotation.*;

@Configuration
public class FeignConfig {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
