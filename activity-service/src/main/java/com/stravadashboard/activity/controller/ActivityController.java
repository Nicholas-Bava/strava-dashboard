package com.stravadashboard.activity.controller;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/activity/cumulative")
    public ResponseEntity<BaseResponse> getCumulativeActivityChart (
            @RequestBody ActivityRequest activityRequest
    ) {
        BaseResponse response = this.activityService.getCumulativeActivityChart(activityRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/activity/list")
    public ResponseEntity<BaseResponse> getActivities (
            @RequestBody ActivityRequest activityRequest
    ) {
        BaseResponse response = this.activityService.getActivities(activityRequest);
        return ResponseEntity.ok(response);
    }

}
