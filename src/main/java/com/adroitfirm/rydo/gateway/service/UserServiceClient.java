package com.adroitfirm.rydo.gateway.service;

import java.util.Map;

import com.adroitfirm.rydo.dto.UserDto;
import com.adroitfirm.rydo.utility.ApiResponse;

import reactor.core.publisher.Mono;

public interface UserServiceClient {

	public Mono<ApiResponse<UserDto>> findByPhone(String phone);
	
	public Mono<ApiResponse<String>> generateOtp(Map<String, String> body);
	public Mono<ApiResponse<String>> validateOtp(Map<String, String> body);
	public Mono<ApiResponse<UserDto>> signup(Map<String, String> body);
}
