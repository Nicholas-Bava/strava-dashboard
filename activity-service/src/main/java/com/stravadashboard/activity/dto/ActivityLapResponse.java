package com.stravadashboard.activity.dto;

import com.stravadashboard.common.entity.ActivityLap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ActivityLapResponse extends BaseResponse{
    List<ActivityLap> activityLapList;
}
