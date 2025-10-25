package com.adroitfirm.rydo.gateway.service.impl;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.adroitfirm.rydo.dto.UserDto;
import com.adroitfirm.rydo.gateway.service.UserServiceClient;
import com.adroitfirm.rydo.gateway.util.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Service
public class UserServiceClientImpl implements UserServiceClient {

	@Value("${rydo.service-url.user}")
	private String userServiceUrl;
	
	private WebClient webClient;
	
	public UserServiceClientImpl(WebClient webClient, ObjectMapper mapper) {
		this.webClient = webClient;
	}

	@Override
	public Mono<ApiResponse<UserDto>> findByPhone(String phone) {
		URI uri = UriComponentsBuilder.fromUriString(userServiceUrl + "/api/users/phone/{phone}")
				.build(phone);
		
		Mono<ApiResponse<UserDto>> response = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserDto>>() {});
		return response;
	}

	@Override
	public Mono<ApiResponse<String>> generateOtp(Map<String, String> body) {
		Mono<ApiResponse<String>> response = webClient
				.post()
                .uri(userServiceUrl + "/api/auth/send-otp")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {});
		return response;
	}

	@Override
	public Mono<ApiResponse<String>> validateOtp(Map<String, String> body) {
		Mono<ApiResponse<String>> response = webClient
				.post()
                .uri(userServiceUrl + "/api/auth/verify-otp")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {});
		return response;
	}

	@Override
	public Mono<ApiResponse<UserDto>> signup(Map<String, String> body) {
		Mono<ApiResponse<UserDto>> response = webClient
				.post()
                .uri(userServiceUrl + "/api/profile/signup")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserDto>>() {});
		return response;
	}
}
