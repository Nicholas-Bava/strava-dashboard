package com.stravadashboard.sync.repository;

import com.stravadashboard.sync.entity.StravaToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StravaTokenRepository extends JpaRepository<StravaToken, Long> {

}
