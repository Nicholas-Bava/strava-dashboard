package com.stravadashboard.sync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "strava_token")
public class StravaToken {
    @Id
    private Long id = 1L;
    private String accessToken;
    private String refreshToken;
    private long expiresAt;
}
