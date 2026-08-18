package com.stravadashboard.sync.service;

import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.repository.ActivityRepository;
import com.stravadashboard.sync.client.StravaClient;
import com.stravadashboard.sync.mapper.ActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SyncServiceImpl implements SyncService {

    private final StravaClient stravaClient;
    private final ActivityMapper activityMapper;
    private final ActivityRepository activityRepository;

    public SyncServiceImpl(StravaClient stravaClient, ActivityMapper activityMapper, ActivityRepository activityRepository) {
        this.stravaClient = stravaClient;
        this.activityMapper = activityMapper;
        this.activityRepository = activityRepository;
    }

    @Override
    public int initialLoad(String after) {

        int page = 1;
        int perPage = 200;
        int lastCount = 200;
        int runningCount = 0;

        LocalDate date = LocalDate.parse(after);
        long epochAfter = date.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        long epochBefore = Instant.now().getEpochSecond();

        while (lastCount == 200){
            List<Map<String, Object>> activities = stravaClient.getActivities(epochAfter, epochBefore, page, perPage);
            lastCount = activities.size();

            if (lastCount > 0){
                List<Activity> entityList = activities.stream()
                        .map(activityMapper::mapToActivity)
                        .toList();

                activityRepository.saveAll(entityList);

                log.info("Page {} — fetched {} activities, saved to DB", page, lastCount);
            }
            runningCount += lastCount;
            page++;
        }
        return runningCount;
    }
}
