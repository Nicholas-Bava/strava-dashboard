package com.stravadashboard.activity.service;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.constants.*;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.dto.CumulativeResponse;
import com.stravadashboard.activity.processor.ActivityProcessor;
import com.stravadashboard.activity.processor.CumulativeProcessor;
import com.stravadashboard.activity.processor.ProcessorOrchestrator;
import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService{

    private final ActivityRepository activityRepository;
    private final CumulativeProcessor cumulativeProcessor;
    private final ActivityProcessor activityProcessor;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
        this.activityProcessor = new ActivityProcessor(activityRepository);
        this.cumulativeProcessor = new CumulativeProcessor(activityRepository);
    }

    @Override
    public BaseResponse getCumulativeActivityChart(ActivityRequest activityRequest) {
        ProcessorOrchestrator processorOrchestrator = new ProcessorOrchestrator(cumulativeProcessor);
        return processorOrchestrator.orchestrate(activityRequest);
    }

    @Override
    public BaseResponse getActivities(ActivityRequest activityRequest) {
        ProcessorOrchestrator processorOrchestrator = new ProcessorOrchestrator(activityProcessor);
        return processorOrchestrator.orchestrate(activityRequest);
    }
}
