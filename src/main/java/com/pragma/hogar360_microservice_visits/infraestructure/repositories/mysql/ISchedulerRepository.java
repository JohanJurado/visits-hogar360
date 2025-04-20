package com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_visits.infraestructure.entities.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ISchedulerRepository extends JpaRepository<SchedulerEntity, Long> {
    @Query("""
        SELECT COUNT(s) > 0 FROM SchedulerEntity s\s
        WHERE s.idHouse = :idHouse\s
        AND (
            (s.startDate < :endDate AND s.endDate > :startDate)
        )
   \s""")
    boolean existByIdAndRangeTime(
            @Param("idHouse") Long idHouse,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}
