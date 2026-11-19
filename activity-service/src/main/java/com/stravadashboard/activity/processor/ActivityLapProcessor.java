package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.client.SyncClient;
import com.stravadashboard.activity.dto.ActivityLapResponse;
import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.exception.ActivityNotFoundException;
import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.entity.ActivityLap;
import com.stravadashboard.common.repository.ActivityLapRepository;
import com.stravadashboard.common.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
public class ActivityLapProcessor extends BaseProcessor<ActivityLap>{

    private final ActivityLapRepository activityLapRepository;
    private final ActivityRepository activityRepository;
    private final SyncClient syncClient;

    public ActivityLapProcessor(ActivityLapRepository activityLapRepository,
                                ActivityRepository activityRepository,
                                SyncClient syncClient) {
        this.activityLapRepository = activityLapRepository;
        this.activityRepository = activityRepository;
        this.syncClient = syncClient;
    }

    @Override
    public List<ActivityLap> retrieveData(ActivityRequest activityRequest) {
        Long activityId = activityRequest.getId();
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException(activityId));

        if (activity.getLapsSyncedAt() == null){
            boolean lapsSynced = syncClient.syncLaps(activityId);
            if (lapsSynced) {
                log.info("Laps synced for activity {}", activityId);
            } else {
                log.warn("Laps could not be synced for activity {}", activityId);
            }
        }
        return activityLapRepository.findByActivityId(activityId);
    }

    @Override
    public BaseResponse processAggregate(List<ActivityLap> laps, ActivityRequest activityRequest) {
        return null;
    }

    @Override
    public BaseResponse processDrillThrough(List<ActivityLap> laps) {
        ActivityLapResponse activityLapResponse = new ActivityLapResponse();
        activityLapResponse.setActivityLapList(laps);
        return activityLapResponse;
    }
}
