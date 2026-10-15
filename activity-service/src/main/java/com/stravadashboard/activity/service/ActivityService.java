package com.stravadashboard.activity.service;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.dto.CumulativeResponse;

import java.util.List;

public interface ActivityService {

    public BaseResponse getCumulativeActivityChart(ActivityRequest activityRequest);

    public BaseResponse getActivities(ActivityRequest activityRequest);

    public List<Integer> getActivityYears(ActivityRequest activityRequest);
}
