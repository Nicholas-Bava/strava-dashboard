package com.stravadashboard.activity.dto;

import com.stravadashboard.common.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ActivityResponse extends BaseResponse{
    private List<Activity> activities;
}
