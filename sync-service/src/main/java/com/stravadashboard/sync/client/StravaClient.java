package com.stravadashboard.sync.client;

import com.stravadashboard.sync.entity.StravaToken;

import com.stravadashboard.sync.repository.StravaTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * Handles authentication and API communication with Strava.
 * Manages OAuth token lifecycle including refresh and persistence.
 */
@Component
@Slf4j
public class StravaClient {

    private final RestClient restClient = RestClient.create();

    @Value("${strava.client-id}")
    private String clientId;

    @Value("${strava.client-secret}")
    private String clientSecret;

    @Value("${strava.refresh-token}")
    private String initialRefreshToken;

    private StravaToken stravaToken;

    private StravaTokenRepository stravaTokenRepository;

    public StravaClient(StravaTokenRepository stravaTokenRepository) {
        this.stravaTokenRepository = stravaTokenRepository;
    }

    /**
     * Returns the current refresh token from the database, falling back
     * to the initial token from application config on first run.
     */
    private String getRefreshToken() {
        String refreshToken = stravaTokenRepository.findById(1L)
                .map(StravaToken::getRefreshToken)
                .orElse(initialRefreshToken);
        return refreshToken;
    }

    /**
     * Exchanges the refresh token for a new access token via Strava's OAuth endpoint.
     * Persists the new tokens to the database for future use.
     *
     * @throws RuntimeException if Strava returns an empty response
     */
    public void refreshAccessToken() {

        String refToken = getRefreshToken();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refToken);

        Map response = restClient.post()
                .uri("https://www.strava.com/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body)
                .retrieve()
                .body(Map.class);

        if (response == null) {
            throw new RuntimeException("Empty response from Strava token endpoint");
        }

        this.stravaToken = stravaTokenRepository.findById(1L)
                .orElse(new StravaToken());
        this.stravaToken.setId(1L);
        this.stravaToken.setAccessToken((String) response.get("access_token"));
        this.stravaToken.setRefreshToken(response.get("refresh_token") != null ? (String) response.get("refresh_token") : refToken);
        this.stravaToken.setExpiresAt(((Number) response.get("expires_at")).longValue());

        stravaTokenRepository.save(this.stravaToken);

        log.info("Access token refreshed and saved to database. New expires_at: {}", this.stravaToken.getExpiresAt());
    }

    /**
     * Fetches a page of activities from the Strava API within the given time range.
     * Automatically refreshes the access token if expired.
     *
     * @param after   epoch timestamp — only return activities after this time
     * @param before  epoch timestamp — only return activities before this time
     * @param page    page number (starts at 1)
     * @param perPage number of activities per page (max 200)
     * @return list of raw activity data as key-value maps
     * @throws RuntimeException if the access token is null after refresh attempt
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getActivities(long after, long before, int page, int perPage) {

        if (stravaToken == null || stravaToken.getExpiresAt() < System.currentTimeMillis() / 1000) {
            refreshAccessToken();
            log.info("Access token refreshed");
        }

        if (stravaToken != null) {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("www.strava.com")
                            .path("/api/v3/athlete/activities")
                            .queryParam("after", after)
                            .queryParam("before", before)
                            .queryParam("page", page)
                            .queryParam("per_page", perPage)
                            .build())
                    .header("Authorization", "Bearer " + stravaToken.getAccessToken())
                    .retrieve()
                    .body(List.class);
        } else {
            throw new RuntimeException("Access token is null after refresh attempt");
        }
    }
}
