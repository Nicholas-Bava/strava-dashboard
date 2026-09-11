package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;

import java.util.List;

public class CumulativeProcessor extends BaseProcessor{

    @Override
    public List<Activity> retrieveData(ActivityRequest activityRequest) {
        return List.of();
    }

    @Override
    public BaseResponse processAggregate(List<Activity> activities, ActivityRequest activityRequest) {
        return null;
    }
}
