package com.adroitfirm.rydo.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.adroitfirm.rydo.gateway.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ApiResponse<String>> handleWebClientResponseException(WebClientResponseException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getResponseBodyAs(ApiResponse.class));
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleWebClientRequestException(WebClientRequestException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Downstream service unavailable: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Unexpected error: " + ex.getMessage()));
    }
}
