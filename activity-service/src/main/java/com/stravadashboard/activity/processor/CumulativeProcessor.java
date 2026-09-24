package com.stravadashboard.activity.processor;

import com.stravadashboard.activity.constants.ActivityConstants;
import com.stravadashboard.activity.dto.ActivityRequest;
import com.stravadashboard.activity.dto.BaseResponse;
import com.stravadashboard.activity.dto.CumulativeResponse;
import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.common.repository.ActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

@Slf4j
public class CumulativeProcessor extends BaseProcessor{

    private final ActivityRepository activityRepository;

    public CumulativeProcessor(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> retrieveData(ActivityRequest activityRequest) {
        List<Integer> years = activityRequest.getActivityFilters().getYears();

        Integer minYear = Collections.min(years);
        Integer maxYear = Collections.max(years);

        List<Activity> activities = activityRepository.findByStartDateBetween(
                LocalDate.of(minYear, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC),
                LocalDate.of(maxYear, 12, 31).atStartOfDay().toInstant(ZoneOffset.UTC)
        );

        log.info("Data loaded from db");
        return activities;
    }

    @Override
    public BaseResponse processAggregate(List<Activity> activities, ActivityRequest activityRequest) {

        CumulativeResponse cumulativeResponse = new CumulativeResponse();

        String type = activityRequest.getActivityFilters().getType();
        String metric = activityRequest.getMetric();
        List<Integer> years = activityRequest.getActivityFilters().getYears();

        List<CumulativeResponse.YearLine> yearLines = new ArrayList<>();
        for (int year: years) {
            List<Activity> sortedYearActivities = activities.stream()
                    .filter(a -> a.getStartDate().atZone(ZoneId.of("America/New_York")).getYear() == year)
                    .filter(a -> type == null || a.getType().equals(type))
                    .sorted(Comparator.comparing(Activity::getStartDate))
                    .toList();

            List<CumulativeResponse.DataPoint> dataPoints = new ArrayList<>();
            Double cumulativeYearlyTotal = 0.0;

            for (Activity a: sortedYearActivities) {
                switch (metric) {
                    case ActivityConstants.METRIC_DISTANCE:
                        cumulativeYearlyTotal += (a.getDistance() / ActivityConstants.METERS_TO_MILES);
                        break;
                    case ActivityConstants.METRIC_ELEVATION:
                        cumulativeYearlyTotal += (a.getTotalElevationGain() * ActivityConstants.METERS_TO_FEET);
                        break;
                    default:
                        log.warn("No metric comparison found for this request");
                        break;
                }
                // Day of year - may phase out using date implementation... keep for now
                int dayOfYear = a.getStartDate()
                        .atZone(ZoneId.of(ActivityConstants.ZONE_US_EASTERN))
                        .getDayOfYear();
                // Date
                Date startDate = Date.from(a.getStartDate());
                dataPoints.add(new CumulativeResponse.DataPoint(dayOfYear, cumulativeYearlyTotal, startDate));
            }
            yearLines.add(new CumulativeResponse.YearLine(year, dataPoints));
        }
        cumulativeResponse.setLines(yearLines);
        cumulativeResponse.setMetric(metric);
        return cumulativeResponse;
    }

    public BaseResponse processDrillThrough(List<Activity> activities) {
        // Implementation for drill-through processing
        return null;
    }
}
