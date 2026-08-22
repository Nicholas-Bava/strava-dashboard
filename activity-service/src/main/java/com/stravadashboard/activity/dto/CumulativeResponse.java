package com.stravadashboard.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CumulativeResponse {
    private String metric;
    private List<YearLine> lines;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class YearLine {
        private int year;
        private List<DataPoint> dataPoints;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataPoint {
        private int dayOfYear;
        private double cumulative;
    }

}
