package com.stravadashboard.sync.mapper;

import com.stravadashboard.common.entity.Activity;
import com.stravadashboard.sync.utils.MapperUtils;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class ActivityMapper {

    MapperUtils mapperUtils = new MapperUtils();

    @SuppressWarnings("unchecked")
    public Activity mapToActivity(Map<String, Object> raw) {
        Activity a = new Activity();

        // IDs
        a.setId(mapperUtils.getLong(raw, "id"));
        a.setResourceState(mapperUtils.getInteger(raw, "resource_state"));

        Map<String, Object> athlete = (Map<String, Object>) raw.get("athlete");
        if (athlete != null) {
            a.setAthleteId(mapperUtils.getLong(athlete, "id"));
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
        a.setDistance(mapperUtils.getDouble(raw, "distance"));
        a.setMovingTime(mapperUtils.getInteger(raw, "moving_time"));
        a.setElapsedTime(mapperUtils.getInteger(raw, "elapsed_time"));
        a.setTotalElevationGain(mapperUtils.getDouble(raw, "total_elevation_gain"));

        // Dates
        String startDate = (String) raw.get("start_date");
        if (startDate != null) {
            a.setStartDate(Instant.parse(startDate));
        }
        a.setStartDateLocal((String) raw.get("start_date_local"));
        a.setTimezone((String) raw.get("timezone"));
        a.setUtcOffset(mapperUtils.getDouble(raw, "utc_offset"));

        // Location strings
        a.setLocationCity((String) raw.get("location_city"));
        a.setLocationState((String) raw.get("location_state"));
        a.setLocationCountry((String) raw.get("location_country"));

        // Counts
        a.setAchievementCount(mapperUtils.getInteger(raw, "achievement_count"));
        a.setKudosCount(mapperUtils.getInteger(raw, "kudos_count"));
        a.setCommentCount(mapperUtils.getInteger(raw, "comment_count"));
        a.setAthleteCount(mapperUtils.getInteger(raw, "athlete_count"));
        a.setPhotoCount(mapperUtils.getInteger(raw, "photo_count"));
        a.setTotalPhotoCount(mapperUtils.getInteger(raw, "total_photo_count"));
        a.setPrCount(mapperUtils.getInteger(raw, "pr_count"));

        // Map / polyline
        Map<String, Object> map = (Map<String, Object>) raw.get("map");
        if (map != null) {
            a.setMapId((String) map.get("id"));
            a.setSummaryPolyline((String) map.get("summary_polyline"));
        }

        // Booleans
        a.setTrainer(mapperUtils.getBoolean(raw, "trainer"));
        a.setCommute(mapperUtils.getBoolean(raw, "commute"));
        a.setManual(mapperUtils.getBoolean(raw, "manual"));
        a.setPrivateActivity(mapperUtils.getBoolean(raw, "private"));
        a.setFlagged(mapperUtils.getBoolean(raw, "flagged"));
        a.setFromAcceptedTag(mapperUtils.getBoolean(raw, "from_accepted_tag"));
        a.setHasHeartrate(mapperUtils.getBoolean(raw, "has_heartrate"));
        a.setHeartrateOptOut(mapperUtils.getBoolean(raw, "heartrate_opt_out"));
        a.setDisplayHideHeartrateOption(mapperUtils.getBoolean(raw, "display_hide_heartrate_option"));
        a.setDeviceWatts(mapperUtils.getBoolean(raw, "device_watts"));
        a.setHasKudoed(mapperUtils.getBoolean(raw, "has_kudoed"));

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
        a.setAverageSpeed(mapperUtils.getDouble(raw, "average_speed"));
        a.setMaxSpeed(mapperUtils.getDouble(raw, "max_speed"));
        a.setAverageCadence(mapperUtils.getDouble(raw, "average_cadence"));
        a.setAverageHeartrate(mapperUtils.getDouble(raw, "average_heartrate"));
        a.setMaxHeartrate(mapperUtils.getDouble(raw, "max_heartrate"));
        a.setAverageWatts(mapperUtils.getDouble(raw, "average_watts"));
        a.setMaxWatts(mapperUtils.getInteger(raw, "max_watts"));
        a.setWeightedAverageWatts(mapperUtils.getInteger(raw, "weighted_average_watts"));
        a.setKilojoules(mapperUtils.getDouble(raw, "kilojoules"));
        a.setSufferScore(mapperUtils.getDouble(raw, "suffer_score"));

        // Elevation
        a.setElevHigh(mapperUtils.getDouble(raw, "elev_high"));
        a.setElevLow(mapperUtils.getDouble(raw, "elev_low"));

        // Upload info
        a.setUploadId(mapperUtils.getLong(raw, "upload_id"));
        a.setExternalId((String) raw.get("external_id"));

        return a;
    }

}
