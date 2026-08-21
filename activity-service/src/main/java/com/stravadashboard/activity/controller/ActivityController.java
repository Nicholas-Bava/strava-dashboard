package com.stravadashboard.activity.controller;

import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.ActivityResponse;
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

    @PostMapping("/activity/get-activities")
    public ResponseEntity<ActivityResponse> getActivities (
            @RequestBody ActivityRequest activityRequest
    ) {
        ActivityResponse response = this.activityService.getActivities(activityRequest);
        return ResponseEntity.ok(response);
    }

}
