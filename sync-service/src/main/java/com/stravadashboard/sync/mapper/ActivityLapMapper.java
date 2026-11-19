package com.stravadashboard.sync.mapper;

import com.stravadashboard.common.entity.ActivityLap;
import com.stravadashboard.sync.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class ActivityLapMapper {

    MapperUtils mapperUtils = new MapperUtils();

    @SuppressWarnings("unchecked")
    public ActivityLap mapToActivityLap(Map<String, Object> lapData) {
        ActivityLap al = new ActivityLap();

        al.setId(mapperUtils.getLong(lapData, "id"));
        al.setName((String) lapData.get("name"));
        al.setResourceState(mapperUtils.getInteger(lapData, "resource_state"));
        al.setLapIndex(mapperUtils.getInteger(lapData, "lap_index"));
        al.setSplitNumber(mapperUtils.getInteger(lapData, "split"));

        al.setElapsedTime(mapperUtils.getInteger(lapData, "elapsed_time"));
        al.setMovingTime(mapperUtils.getInteger(lapData, "moving_time"));
        al.setDistance(mapperUtils.getDouble(lapData, "distance"));
        al.setTotalElevationGain(mapperUtils.getDouble(lapData, "total_elevation_gain"));

        Object startDate = lapData.get("start_date");
        if (startDate != null) {
            al.setStartDate(Instant.parse(startDate.toString()));
        }
        al.setStartDateLocal((String) lapData.get("start_date_local"));
        al.setStartIndex(mapperUtils.getInteger(lapData, "start_index"));
        al.setEndIndex(mapperUtils.getInteger(lapData, "end_index"));

        al.setAverageSpeed(mapperUtils.getDouble(lapData, "average_speed"));
        al.setMaxSpeed(mapperUtils.getDouble(lapData, "max_speed"));
        al.setAverageCadence(mapperUtils.getDouble(lapData, "average_cadence"));
        al.setDeviceWatts((Boolean) lapData.get("device_watts"));
        al.setAverageWatts(mapperUtils.getDouble(lapData, "average_watts"));

        // Nested objects: "activity": {"id": ...} and "athlete": {"id": ...}
        Map<String, Object> activity = (Map<String, Object>) lapData.get("activity");
        if (activity != null) {
            al.setActivityId(mapperUtils.getLong(activity, "id"));
        }
        Map<String, Object> athlete = (Map<String, Object>) lapData.get("athlete");
        if (athlete != null) {
            al.setAthleteId(mapperUtils.getLong(athlete, "id"));
        }

        return al;
    }

}
