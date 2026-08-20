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

/**
 * Handles syncing Strava activity data to the local database.
 */
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

    /**
     * Performs a full backfill of activities from the given date to now.
     *
     * @param after start date in yyyy-MM-dd format
     * @return number of activities saved
     */
    @Override
    public int initialLoad(String after) {
        int page = 1;
        int perPage = 200;

        LocalDate date = LocalDate.parse(after);
        long epochAfter = date.atStartOfDay(ZoneOffset.UTC).toEpochSecond();
        long epochBefore = Instant.now().getEpochSecond();

        return callStravaClientForActivities(epochAfter, epochBefore, page, perPage);
    }

    /**
     * Syncs new activities since the most recent activity in the database.
     *
     * @return number of new activities saved
     * @throws IllegalStateException if no activities exist in the database
     */
    @Override
    public int loadDataSinceLastActivityDate() {
        int page = 1;
        int perPage = 200;

        long epochAfter = getMostRecentActivityDate();
        if (epochAfter == 0L){
            throw new IllegalStateException("No activities found in database. Run initial load first.");
        }

        long epochBefore = Instant.now().getEpochSecond();
        return callStravaClientForActivities(epochAfter, epochBefore, page, perPage);
    }

    /**
     * Returns the epoch timestamp of the most recent activity, or 0 if none exist.
     */
    private long getMostRecentActivityDate (){
        return activityRepository.findTopByOrderByStartDateDesc()
                .map(a -> a.getStartDate().getEpochSecond())
                .orElse(0L);
    }

    /**
     * Paginates through the Strava API and saves all activities in the given time range.
     *
     * @param after  epoch timestamp for the start of the range
     * @param before epoch timestamp for the end of the range
     * @param page   starting page number
     * @param perPage number of activities per page (max 200)
     * @return total number of activities saved
     */
    private int callStravaClientForActivities (long after, long before, int page, int perPage) {

        int lastCount = perPage;
        int runningCount = 0;

        while (lastCount == perPage){
            List<Map<String, Object>> activities = stravaClient.getActivities(after, before, page, perPage);
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
