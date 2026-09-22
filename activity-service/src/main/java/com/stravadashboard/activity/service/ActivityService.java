package com.stravadashboard.activity.service;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.dto.CumulativeResponse;

public interface ActivityService {

    public BaseResponse getCumulativeActivityChart(ActivityRequest activityRequest);

    public BaseResponse getActivities(ActivityRequest activityRequest);
}
