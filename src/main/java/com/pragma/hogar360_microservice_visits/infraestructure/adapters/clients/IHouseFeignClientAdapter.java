package com.pragma.hogar360_microservice_visits.infraestructure.adapters.clients;

import com.pragma.hogar360_microservice_visits.domain.ports.out.IHouseFeignClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IHouseFeignClientAdapter implements IHouseFeignClientPort {

    private final IHouseFeignClient houseFeignClient;

    @Override
    public boolean existHouseByEmailSeller(String emailSeller, Long idHouse) {
        return houseFeignClient.validateHouseByEmailSeller(emailSeller, idHouse);
    }
}
