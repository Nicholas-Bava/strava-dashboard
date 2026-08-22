package com.stravadashboard.activity.service;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.CumulativeResponse;

public interface ActivityService {

    public CumulativeResponse getCumulativeActivityChart(ActivityRequest activityRequest);
}
