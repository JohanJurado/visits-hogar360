package com.pragma.hogar360_microservice_visits.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_visits.domain.model.SchedulerModel;
import com.pragma.hogar360_microservice_visits.domain.ports.out.ISchedulerPersistencePort;
import com.pragma.hogar360_microservice_visits.infraestructure.mappers.ISchedulerEntityMapper;
import com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql.ISchedulerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SchedulerPersistenceAdapter implements ISchedulerPersistencePort {

    private final ISchedulerRepository schedulerRepository;
    private final ISchedulerEntityMapper schedulerEntityMapper;

    @Override
    public void save(SchedulerModel schedulerModel) {
        schedulerRepository.save(schedulerEntityMapper.modelToEntity(schedulerModel));
    }

    @Override
    public boolean existByIdAndRangeTime(Long idHouse, LocalDateTime startDate, LocalDateTime endDate) {
        return schedulerRepository.existByIdAndRangeTime(idHouse, startDate, endDate);
    }
}
