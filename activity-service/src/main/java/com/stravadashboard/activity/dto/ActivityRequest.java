package com.stravadashboard.activity.dto;

import com.stravadashboard.activity.constants.RequestMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ActivityRequest {
    private ActivityFilters activityFilters;
    private String metric;
    private String sortBy = "startDate";
    private boolean sortDescending = true;
    private boolean paginated = true;
    private int page = 0;
    private int pageSize = 20;
    private RequestMode mode = RequestMode.AGGREGATE;
}
