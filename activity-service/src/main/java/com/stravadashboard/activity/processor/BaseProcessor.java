package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;

import java.util.List;

public abstract class BaseProcessor<T> {

    public abstract List<T> retrieveData(ActivityRequest activityRequest);
    public abstract BaseResponse processAggregate(List<T> data, ActivityRequest activityRequest);
    public abstract BaseResponse processDrillThrough (List<T> data);

    public final BaseResponse process(ActivityRequest activityRequest) {
        List<T> data = retrieveData(activityRequest);

        switch (activityRequest.getMode()) {
            case AGGREGATE:
                return processAggregate(data, activityRequest);
            case DRILL_THROUGH:
                return processDrillThrough(data);
            default:
                throw new IllegalArgumentException("Unsupported request mode");
        }
    }
}
