package com.stravadashboard.activity.constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public enum RequestMode {
    @JsonProperty("aggregate")
    AGGREGATE,
    @JsonProperty("drill_through")
    DRILL_THROUGH,
    @JsonProperty("download")
    DOWNLOAD
}
