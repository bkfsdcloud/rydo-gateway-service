package com.adroitfirm.rydo.gateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.adroitfirm.rydo.utility.JwtUtils;

@Configuration
public class AppConfig {

	@Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
	
	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	
	@Bean
	JwtUtils jwtUtils(@Value("${jwt.secret}") String secretKey,
	                         @Value("${jwt.expirationMs}") long expirationMs) {
	    return new JwtUtils(secretKey, expirationMs);
	}

}
