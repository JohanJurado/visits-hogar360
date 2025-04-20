package com.pragma.hogar360_microservice_visits.infraestructure.adapters.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "house-service", url = "${feign.house-service.url}")
public interface IHouseFeignClient {

    @GetMapping("/internal/house/validate-house/{emailSeller}/{idHouse}")
    boolean validateHouseByEmailSeller(
            @PathVariable String emailSeller,
            @PathVariable Long idHouse
    );
}
