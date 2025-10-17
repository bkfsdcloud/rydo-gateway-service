package com.adroitfirm.rydo.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	private final JwtAuthEntryPoint authEntryPoint;
	private final JwtAccessDeniedHandler accessDeniedHandler;
	private final JwtAuthFilter jwtAuthFilter;
	
	@Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
        		.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                    .pathMatchers("api/auth/**").permitAll()  // OTP endpoints
                    .anyExchange().authenticated()        // all other requests require JWT
                )
                .addFilterBefore(jwtAuthFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint)
            		.accessDeniedHandler(accessDeniedHandler))
                .build();
    }

}
