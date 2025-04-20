package com.pragma.hogar360_microservice_visits.infraestructure.configurations.feign;

import com.pragma.hogar360_microservice_visits.infraestructure.utils.jwt.JwtUtils;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.InfrastructureConstants.HEADER_PREFIX;
import static com.pragma.hogar360_microservice_visits.infraestructure.utils.constants.InfrastructureConstants.TOKEN_PREFIX;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final JwtUtils jwtUtils;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header(HEADER_PREFIX, TOKEN_PREFIX + jwtUtils.generateMicroserviceToken());
    }
}
