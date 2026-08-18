package com.stravadashboard.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan({"com.stravadashboard.common.entity", "com.stravadashboard.sync.entity"})
@EnableJpaRepositories({"com.stravadashboard.common.repository", "com.stravadashboard.sync.repository"})
public class SyncApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyncApplication.class, args);
    }
}
