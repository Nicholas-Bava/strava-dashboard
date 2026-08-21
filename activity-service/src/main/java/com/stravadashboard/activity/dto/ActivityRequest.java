package com.stravadashboard.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {
    private ActivityFilters activityFilters;
    private String sortBy = "startDate";
    private String sortDirection = "desc";
    private boolean paginated = true;
    private int page = 0;
    private int pageSize = 20;
}
