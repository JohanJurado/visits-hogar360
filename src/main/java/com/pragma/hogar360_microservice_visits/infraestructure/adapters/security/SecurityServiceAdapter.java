package com.pragma.hogar360_microservice_visits.infraestructure.adapters.security;

import com.pragma.hogar360_microservice_visits.domain.ports.out.ISecurityServicePort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityServiceAdapter implements ISecurityServicePort {

    @Override
    public String getAuthenticatedEmail() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}