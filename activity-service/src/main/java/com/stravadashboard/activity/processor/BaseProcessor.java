package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;

import java.util.List;

public abstract class BaseProcessor {
    public abstract List<Activity> retrieveData(ActivityRequest activityRequest);
    public abstract BaseResponse processAggregate(List<Activity> activities, ActivityRequest activityRequest);
}
