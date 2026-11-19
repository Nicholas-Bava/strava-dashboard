package com.stravadashboard.activity.service;

import com.stravadashboard.activity.client.SyncClient;
import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.processor.ActivityLapProcessor;
import com.stravadashboard.activity.processor.ActivityProcessor;
import com.stravadashboard.activity.processor.CumulativeProcessor;
import com.stravadashboard.common.repository.ActivityLapRepository;
import com.stravadashboard.common.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final ActivityLapRepository activityLapRepository;
    private final CumulativeProcessor cumulativeProcessor;
    private final ActivityProcessor activityProcessor;
    private final ActivityLapProcessor activityLapProcessor;


    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityLapRepository activityLapRepository, SyncClient syncClient) {
        this.activityRepository = activityRepository;
        this.activityLapRepository = activityLapRepository;
        this.activityProcessor = new ActivityProcessor(activityRepository);
        this.cumulativeProcessor = new CumulativeProcessor(activityRepository);
        this.activityLapProcessor = new ActivityLapProcessor(activityLapRepository, activityRepository, syncClient);
    }

    @Override
    public BaseResponse getCumulativeActivityChart(ActivityRequest activityRequest) {

        return cumulativeProcessor.process(activityRequest);
    }

    @Override
    public BaseResponse getActivities(ActivityRequest activityRequest) {
        return activityProcessor.process(activityRequest);
    }

    @Override
    public List<Integer> getActivityYears(ActivityRequest activityRequest) {
        String sportType = activityRequest.getActivityFilters().getSportType();
        return activityRepository.findDistinctYearsBySportType(sportType).stream()
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }

    @Override
    public BaseResponse getActivityLaps(ActivityRequest activityRequest) {
        return activityLapProcessor.process(activityRequest);
    }
}
