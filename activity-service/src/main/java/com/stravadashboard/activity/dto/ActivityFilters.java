package com.stravadashboard.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityFilters {
    private String startDate;
    private String endDate;
    private String type;
    private String sportType;
    private String workoutType;
    private Double minDistance;
    private Double maxDistance;
    private List<Integer> years;
}
