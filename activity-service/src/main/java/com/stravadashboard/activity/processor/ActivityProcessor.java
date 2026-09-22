package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.ActivityResponse;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
public class ActivityProcessor extends BaseProcessor{

    private final ActivityRepository activityRepository;

    public ActivityProcessor(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> retrieveData(ActivityRequest activityRequest) {
        Pageable pageable = PageRequest.of(
                activityRequest.getPage(),
                activityRequest.getPageSize(),
                Sort.by(activityRequest.isSortDescending() ? Sort.Direction.DESC : Sort.Direction.ASC, activityRequest.getSortBy())
        );
        return activityRepository.findAll(pageable).getContent();
    }

    @Override
    public BaseResponse processAggregate(List<Activity> activities, ActivityRequest activityRequest) {
        return null;
    }

    public BaseResponse processDrillThrough(List<Activity> activities){
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setActivities(activities);
        return activityResponse;
    }
}
