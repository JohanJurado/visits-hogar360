package com.pragma.hogar360_microservice_visits.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_visits.infraestructure.entities.VisitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVisitRepository  extends JpaRepository<VisitEntity, Long> {

    @Query("""
        SELECT COUNT(v) > 0 FROM VisitEntity v\s
        WHERE v.schedulerEntity.id = :idScheduler\s
        AND v.emailBuyer = :emailBuyer\s
   \s""")
    boolean existByEmailBuyerAndIdScheduler(
            @Param("emailBuyer") String emailBuyer,
            @Param("idScheduler") Long idScheduler);
}
