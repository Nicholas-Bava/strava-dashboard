package com.stravadashboard.activity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException{
    public ActivityNotFoundException(Long id) {
        super("Activity not found: " + id);
    }
}
