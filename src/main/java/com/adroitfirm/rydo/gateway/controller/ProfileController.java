package com.adroitfirm.rydo.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adroitfirm.rydo.gateway.dto.UserDto;
import com.adroitfirm.rydo.gateway.service.UserServiceClient;
import com.adroitfirm.rydo.gateway.util.ApiResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

	 private final UserServiceClient userService;
	    
    @GetMapping
    public Mono<ResponseEntity<ApiResponse<UserDto>>> profile(@RequestHeader("X-USER-ID") String phone) {
    	return userService.findByPhone(phone)
    		.map(response -> ResponseEntity.ok(response));
    }
}
