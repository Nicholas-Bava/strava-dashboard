package com.stravadashboard.activity.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class SyncClient {

    private final RestClient restClient;

    public SyncClient(@Value("${sync-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean syncLaps(Long id) {
        try {
            restClient.post()
                    .uri("/sync/activities/{id}/laps", id)
                    .retrieve()
                    .toBodilessEntity();
            return true;
        } catch (Exception e) {
            log.warn("Lap sync failed for activity {}: {}", id, e.getMessage());
            return false;
        }
    }

}
