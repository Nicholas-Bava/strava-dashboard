package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.constants.RequestMode;
import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;
import org.springframework.stereotype.Component;

import java.util.List;

public class ProcessorOrchestrator {

    public BaseProcessor baseProcessor;

    public ProcessorOrchestrator (BaseProcessor baseProcessor){
        this.baseProcessor = baseProcessor;
    }

    public BaseResponse orchestrate(ActivityRequest activityRequest) {
        List<Activity> activities = baseProcessor.retrieveData(activityRequest);

        switch(activityRequest.getMode()) {
            case AGGREGATE:
                return baseProcessor.processAggregate(activities, activityRequest);
            case DRILL_THROUGH:
                return baseProcessor.processDrillThrough(activities);
            default:
                throw new IllegalArgumentException("Unsupported request mode");
        }
    }
}
