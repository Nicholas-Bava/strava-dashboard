package com.stravadashboard.activity.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SyncClient {

    private final RestClient restClient;

    public SyncClient(@Value("${sync-service.base-url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public boolean syncLaps(Long id) {
        try {
            // Implementation for syncing laps
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
