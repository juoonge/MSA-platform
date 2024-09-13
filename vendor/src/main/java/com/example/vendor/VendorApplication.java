package com.example.vendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.data.jpa.repository.config.*;

@EnableFeignClients
@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
public class VendorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendorApplication.class, args);
    }

}
