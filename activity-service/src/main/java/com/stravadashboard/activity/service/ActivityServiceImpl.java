package com.stravadashboard.activity.service;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.constants.*;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.dto.CumulativeResponse;
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
    private final ProcessorOrchestrator processorOrchestrator;

    public ActivityServiceImpl(ActivityRepository activityRepository, ProcessorOrchestrator processorOrchestrator) {
        this.activityRepository = activityRepository;
        this.processorOrchestrator = processorOrchestrator;
    }

    @Override
    public BaseResponse getCumulativeActivityChart(ActivityRequest activityRequest) {
        return processorOrchestrator.orchestrate(activityRequest);
    }
}
