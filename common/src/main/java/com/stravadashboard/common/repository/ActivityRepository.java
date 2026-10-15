package com.stravadashboard.common.repository;

import com.stravadashboard.common.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findTopByOrderByStartDateDesc();

    List<Activity> findByStartDateBetween(Instant minStartDate, Instant maxStartDate);

    boolean existsById(Long id);

    @Query("SELECT DISTINCT SUBSTRING(a.startDateLocal, 1, 4) FROM Activity a " +
            "WHERE a.sportType = :sportType")
    List<String> findDistinctYearsBySportType(@Param("sportType") String sportType);
}
