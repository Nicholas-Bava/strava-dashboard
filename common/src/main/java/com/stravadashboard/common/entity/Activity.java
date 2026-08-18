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
@Table(name = "activities")
public class Activity {

    @Id
    private Long id;

    private Long athleteId;
    private String name;
    private String type;
    private String sportType;
    private String workoutType;

    private Double distance;
    private Integer movingTime;
    private Integer elapsedTime;
    private Double totalElevationGain;

    private Instant startDate;
    private String startDateLocal;
    private String timezone;
    private Double utcOffset;

    private String locationCity;
    private String locationState;
    private String locationCountry;

    private Integer achievementCount;
    private Integer kudosCount;
    private Integer commentCount;
    private Integer athleteCount;
    private Integer photoCount;
    private Integer totalPhotoCount;

    @Column(length = 10000)
    private String summaryPolyline;
    private String mapId;

    private Boolean trainer;
    private Boolean commute;
    private Boolean manual;

    @Column(name = "is_private")
    private Boolean privateActivity;

    private String visibility;
    private Boolean flagged;
    private String gearId;

    private Double startLat;
    private Double startLng;
    private Double endLat;
    private Double endLng;

    private Double averageSpeed;
    private Double maxSpeed;
    private Double averageCadence;
    private Double averageHeartrate;
    private Double maxHeartrate;
    private Double averageWatts;
    private Integer maxWatts;
    private Integer weightedAverageWatts;
    private Boolean deviceWatts;
    private Double kilojoules;
    private Double sufferScore;

    private Boolean hasHeartrate;
    private Boolean heartrateOptOut;
    private Boolean displayHideHeartrateOption;

    private Double elevHigh;
    private Double elevLow;

    private Long uploadId;
    private String externalId;
    private Boolean fromAcceptedTag;
    private Integer prCount;
    private Boolean hasKudoed;

    private String deviceName;
    private Integer resourceState;

}
