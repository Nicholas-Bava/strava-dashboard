package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessorOrchestrator {

    private final BaseProcessor baseProcessor;

    ProcessorOrchestrator (BaseProcessor baseProcessor){
        this. baseProcessor = baseProcessor;
    }

    public BaseResponse orchestrate(ActivityRequest activityRequest) {
        List<Activity> activities = baseProcessor.retrieveData(activityRequest);
        return baseProcessor.processAggregate(activities, activityRequest);
    }
}
