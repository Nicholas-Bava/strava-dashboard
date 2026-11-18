package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.entity.ActivityLap;
import com.stravadashboard.common.repository.ActivityLapRepository;
import com.stravadashboard.common.repository.ActivityRepository;

import java.util.List;

public class ActivityLapProcessor extends BaseProcessor<ActivityLap>{

    private final ActivityLapRepository activityLapRepository;
    private final ActivityRepository activityRepository;

    public ActivityLapProcessor(ActivityLapRepository activityLapRepository, ActivityRepository activityRepository) {
        this.activityLapRepository = activityLapRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public List<ActivityLap> retrieveData(ActivityRequest activityRequest) {
        Long activityId = activityRequest.getId();
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new IllegalArgumentException("Activity not found: " + activityId));

        if (activity.getLapsSyncedAt() == null){

        }
        return activityLapRepository.findByActivityId(activityId);
    }

    @Override
    public BaseResponse processAggregate(List<ActivityLap> laps, ActivityRequest activityRequest) {
        return null;
    }

    @Override
    public BaseResponse processDrillThrough(List<ActivityLap> laps) {
        return null;
    }
}
