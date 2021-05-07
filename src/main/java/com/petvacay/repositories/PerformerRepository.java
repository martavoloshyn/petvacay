package com.petvacay.repositories;

import com.petvacay.entities.Performer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PerformerRepository extends JpaRepository<Performer, Long> {
    @Query(value = "from Performer p where p not in :unavailablePerformers")
    List<Performer> findByUserIdNotIn(List<Performer> unavailablePerformers);

    @Query(value = "select ud.performer from UnavailableDate ud where (ud.startDate between :startDate and :endDate) or " +
            "(ud.endDate between :startDate and :endDate) or " +
            "(ud.startDate <= :startDate and ud.endDate >= :endDate)")
    List<Performer> findPerformerIdsByAvailableDates(Date startDate, Date endDate);
}
