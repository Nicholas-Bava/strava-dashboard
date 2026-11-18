package com.stravadashboard.common.repository;

import com.stravadashboard.common.entity.ActivityLap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLapRepository extends JpaRepository<ActivityLap, Long> {
    List<ActivityLap> findByActivityId(Long activityId);
}
