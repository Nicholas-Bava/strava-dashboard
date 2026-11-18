package com.stravadashboard.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "activity_laps",
        uniqueConstraints = @UniqueConstraint(columnNames = {"activity_id", "lap_index"})
)
public class ActivityLap {

    @Id
    private Long id;                    // Strava's lap ID, same idea as Activity

    private Long activityId;            // from the nested "activity" object
    private Long athleteId;             // from the nested "athlete" object

    private String name;
    private Integer resourceState;
    private Integer lapIndex;
    private Integer splitNumber;        // JSON key is "split"

    private Integer elapsedTime;        // seconds
    private Integer movingTime;         // seconds
    private Double distance;            // meters
    private Double totalElevationGain;  // meters

    private Instant startDate;
    private String startDateLocal;
    private Integer startIndex;
    private Integer endIndex;

    private Double averageSpeed;        // m/s
    private Double maxSpeed;            // m/s
    private Double averageCadence;
    private Boolean deviceWatts;
    private Double averageWatts;
}
