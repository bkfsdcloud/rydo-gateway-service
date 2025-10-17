package com.adroitfirm.rydo.gateway.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class JwtAccessDeniedHandler implements ServerAccessDeniedHandler {

	@Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        String body = "{\"error\": \"Forbidden\", \"message\": \"" + ex.getMessage() + "\"}";
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
	
}
