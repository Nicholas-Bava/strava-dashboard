package com.stravadashboard.sync.utils;

import java.util.Map;

public class MapperUtils {

    public Double getDouble(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.doubleValue() : null;
    }

    public Integer getInteger(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.intValue() : null;
    }

    public Long getLong(Map<String, Object> map, String key) {
        Number val = (Number) map.get(key);
        return val != null ? val.longValue() : null;
    }

    public Boolean getBoolean(Map<String, Object> map, String key) {
        return (Boolean) map.get(key);
    }
}
