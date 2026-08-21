package com.stravadashboard.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityFilters {
    private String startDate;
    private String endDate;
    private String sportType;
    private String workoutType;
    private Double minDistance;
    private Double maxDistance;
}
