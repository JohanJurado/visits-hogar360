package com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_visits.infraestructure.entities.SchedulerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Query("SELECT s FROM SchedulerEntity s " +
            "WHERE (:idHouse IS NULL OR s.idHouse = :idHouse) " +
            "AND (:startDate IS NULL OR s.startDate = :startDate) " +
            "AND (:endDate IS NULL OR s.endDate = :endDate) " +
            "AND (s.visitsCount < :maxVisitsScheduler) " +
            "AND s.startDate >= SYSDATE")
    List<SchedulerEntity> findSchedulersByFilters(
            @Param("idHouse") Long idHouse,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("maxVisitsScheduler") Long maxVisitsScheduler);

    Optional<SchedulerEntity> findById(Long id);
}
