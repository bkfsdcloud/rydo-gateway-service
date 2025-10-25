package com.adroitfirm.rydo.gateway.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adroitfirm.rydo.dto.UserDto;
import com.adroitfirm.rydo.gateway.service.UserServiceClient;
import com.adroitfirm.rydo.gateway.util.ApiResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserServiceClient userService;
    
    @PostMapping("/signup")
    public Mono<ResponseEntity<ApiResponse<UserDto>>> signup(@RequestBody Map<String, String> body) {
        return userService.signup(body)
        		.map(response -> ResponseEntity.ok(response));
        
    }
    
    @PostMapping("/send-otp")
    public Mono<ResponseEntity<ApiResponse<String>>> sendOtp(@RequestBody Map<String, String> body) {
        return userService.generateOtp(body)
        		.map(response -> ResponseEntity.ok(response));
        
    }

    @PostMapping("/verify-otp")
    public Mono<ResponseEntity<ApiResponse<String>>> verifyOtp(@RequestBody Map<String, String> body) {
        return userService.validateOtp(body)
        		.map(response -> ResponseEntity.ok(response));
    }
}
