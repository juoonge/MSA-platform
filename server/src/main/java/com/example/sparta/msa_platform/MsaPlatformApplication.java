package com.example.sparta.msa_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsaPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaPlatformApplication.class, args);
	}

}
