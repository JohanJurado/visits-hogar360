package com.pragma.hogar360_microservice_visits.domain.ports.out;

public interface IHouseFeignClientPort {
    boolean existHouseByEmailSeller(String emailSeller, Long idHouse);
}
