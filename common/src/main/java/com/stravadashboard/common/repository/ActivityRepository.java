package com.stravadashboard.common.repository;

import com.stravadashboard.common.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Optional<Activity> findTopByOrderByStartDateDesc();

    boolean existsById(Long id);
}
