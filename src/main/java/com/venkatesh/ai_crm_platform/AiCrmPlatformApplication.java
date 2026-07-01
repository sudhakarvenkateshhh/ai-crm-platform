package com.venkatesh.ai_crm_platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class AiCrmPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiCrmPlatformApplication.class, args);
	}

}
