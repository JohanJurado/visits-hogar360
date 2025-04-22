package com.pragma.hogar360_microservice_visits.infraestructure.configurations;

import com.pragma.hogar360_microservice_visits.domain.ports.in.ISchedulerServicePort;
import com.pragma.hogar360_microservice_visits.domain.ports.in.IVisitServicePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IHouseFeignClientPort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISecurityServicePort;
import com.pragma.hogar360_microservice_visits.domain.ports.out.IVisitPersistencePort;
import com.pragma.hogar360_microservice_visits.domain.usecases.SchedulerUseCase;
import com.pragma.hogar360_microservice_visits.domain.usecases.VisitUseCase;
import com.pragma.hogar360_microservice_visits.infraestructure.adapters.clients.IHouseFeignClient;
import com.pragma.hogar360_microservice_visits.infraestructure.adapters.clients.IHouseFeignClientAdapter;
import com.pragma.hogar360_microservice_visits.infraestructure.adapters.persistence.SchedulerPersistenceAdapter;
import com.pragma.hogar360_microservice_visits.infraestructure.adapters.persistence.VisitPersistenceAdapter;
import com.pragma.hogar360_microservice_visits.infraestructure.adapters.security.SecurityServiceAdapter;
import com.pragma.hogar360_microservice_visits.infraestructure.mappers.ISchedulerEntityMapper;
import com.pragma.hogar360_microservice_visits.infraestructure.mappers.IVisitEntityMapper;
import com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql.ISchedulerRepository;
import com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql.IVisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ISchedulerEntityMapper schedulerEntityMapper;
    private final IVisitEntityMapper visitEntityMapper;

    private final ISchedulerRepository schedulerRepository;
    private final IVisitRepository visitRepository;

    private final IHouseFeignClient houseFeignClient;

    @Bean
    public IVisitServicePort visitServicePort(){
        return new VisitUseCase(visitPersistencePort(), schedulerPersistencePort());
    }

    @Bean
    public ISchedulerServicePort schedulerServicePort(){
        return new SchedulerUseCase(schedulerPersistencePort(), houseFeignClientPort(), securityServicePort());
    }

    @Bean
    public IVisitPersistencePort visitPersistencePort(){
        return new VisitPersistenceAdapter(schedulerPersistencePort(), visitEntityMapper, visitRepository);
    }
    
    @Bean
    public ISchedulerPersistencePort schedulerPersistencePort(){
        return new SchedulerPersistenceAdapter(schedulerRepository, schedulerEntityMapper);
    }

    @Bean
    public IHouseFeignClientPort houseFeignClientPort(){
        return new IHouseFeignClientAdapter(houseFeignClient);
    }

    public ISecurityServicePort securityServicePort(){
        return new SecurityServiceAdapter();
    }
}

