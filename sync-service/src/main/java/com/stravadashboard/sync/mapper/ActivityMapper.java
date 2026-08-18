package com.stravadashboard.sync.mapper;

import com.stravadashboard.common.entity.Activity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class ActivityMapper {
    public Activity mapToActivity(Map<String, Object> raw) {
        Activity a = new Activity();

        // IDs
        a.setId(getLong(raw, "id"));
        a.setResourceState(getInteger(raw, "resource_state"));

        Map<String, Object> athlete = (Map<String, Object>) raw.get("athlete");
        if (athlete != null) {
            a.setAthleteId(getLong(athlete, "id"));
        }

        // Core info
        a.setName((String) raw.get("name"));
        a.setType((String) raw.get("type"));

        Object sportType = raw.get("sport_type");
        a.setSportType(sportType != null ? sportType.toString() : null);

        Object workoutType = raw.get("workout_type");
        a.setWorkoutType(workoutType != null ? workoutType .toString() : null);

        a.setDeviceName((String) raw.get("device_name"));

        // Distance and time
        a.setDistance(getDouble(raw, "distance"));
        a.setMovingTime(getInteger(raw, "moving_time"));
        a.setElapsedTime(getInteger(raw, "elapsed_time"));
        a.setTotalElevationGain(getDouble(raw, "total_elevation_gain"));

        // Dates
        String startDate = (String) raw.get("start_date");
        if (startDate != null) {
            a.setStartDate(Instant.parse(startDate));
        }
        a.setStartDateLocal((String) raw.get("start_date_local"));
        a.setTimezone((String) raw.get("timezone"));
        a.setUtcOffset(getDouble(raw, "utc_offset"));

        // Location strings
        a.setLocationCity((String) raw.get("location_city"));
        a.setLocationState((String) raw.get("location_state"));
        a.setLocationCountry((String) raw.get("location_country"));

        // Counts
        a.setAchievementCount(getInteger(raw, "achievement_count"));
        a.setKudosCount(getInteger(raw, "kudos_count"));
        a.setCommentCount(getInteger(raw, "comment_count"));
        a.setAthleteCount(getInteger(raw, "athlete_count"));
        a.setPhotoCount(getInteger(raw, "photo_count"));
        a.setTotalPhotoCount(getInteger(raw, "total_photo_count"));
        a.setPrCount(getInteger(raw, "pr_count"));

        // Map / polyline
        Map<String, Object> map = (Map<String, Object>) raw.get("map");
        if (map != null) {
            a.setMapId((String) map.get("id"));
            a.setSummaryPolyline((String) map.get("summary_polyline"));
        }

        // Booleans
        a.setTrainer(getBoolean(raw, "trainer"));
        a.setCommute(getBoolean(raw, "commute"));
        a.setManual(getBoolean(raw, "manual"));
        a.setPrivateActivity(getBoolean(raw, "private"));
        a.setFlagged(getBoolean(raw, "flagged"));
        a.setFromAcceptedTag(getBoolean(raw, "from_accepted_tag"));
        a.setHasHeartrate(getBoolean(raw, "has_heartrate"));
        a.setHeartrateOptOut(getBoolean(raw, "heartrate_opt_out"));
        a.setDisplayHideHeartrateOption(getBoolean(raw, "display_hide_heartrate_option"));
        a.setDeviceWatts(getBoolean(raw, "device_watts"));
        a.setHasKudoed(getBoolean(raw, "has_kudoed"));

        // Gear and visibility
        a.setVisibility((String) raw.get("visibility"));
        a.setGearId((String) raw.get("gear_id"));

        // Lat/lng
        List<Double> startLatLng = (List<Double>) raw.get("start_latlng");
        if (startLatLng != null && startLatLng.size() == 2) {
            a.setStartLat(startLatLng.get(0));
            a.setStartLng(startLatLng.get(1));
        }
        List<Double> endLatLng = (List<Double>) raw.get("end_latlng");
        if (endLatLng != null && endLatLng.size() == 2) {
            a.setEndLat(endLatLng.get(0));
            a.setEndLng(endLatLng.get(1));
        }

        // Speed and performance
        a.setAverageSpeed(getDouble(raw, "average_speed"));
        a.setMaxSpeed(getDouble(raw, "max_speed"));
        a.setAverageCadence(getDouble(raw, "average_cadence"));
        a.setAverageHeartrate(getDouble(raw, "average_heartrate"));
        a.setMaxHeartrate(getDouble(raw, "max_heartrate"));
        a.setAverageWatts(getDouble(raw, "average_watts"));
        a.setMaxWatts(getInteger(raw, "max_watts"));
        a.setWeightedAverageWatts(getInteger(raw, "weighted_average_watts"));
        a.setKilojoules(getDouble(raw, "kilojoules"));
        a.setSufferScore(getDouble(raw, "suffer_score"));

        // Elevation
        a.setElevHigh(getDouble(raw, "elev_high"));
        a.setElevLow(getDouble(raw, "elev_low"));

        // Upload info
        a.setUploadId(getLong(raw, "upload_id"));
        a.setExternalId((String) raw.get("external_id"));

        return a;
    }

    private Double getDouble(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.doubleValue() : null;
    }

    private Integer getInteger(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.intValue() : null;
    }

    private Long getLong(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.longValue() : null;
    }

    private Boolean getBoolean(Map<String, Object> map, String key) {
        return (Boolean) map.get(key);
    }
}
