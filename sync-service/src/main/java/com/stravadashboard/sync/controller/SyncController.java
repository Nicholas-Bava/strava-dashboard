package com.stravadashboard.sync.controller;

import com.stravadashboard.sync.service.SyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncController {

    private final SyncService syncService;

    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @PostMapping("/sync/initial-load")
    public ResponseEntity<String> initialLoad(
            @RequestParam(required = false, defaultValue = "2020-01-01") String after
    ) {
        int count = syncService.initialLoad(after);
        return ResponseEntity.ok("Initial load completed. " + count + " activities synced.");
    }

    @PostMapping("/sync/refresh-data")
    public ResponseEntity<String> refreshData(){
        try {
            int count = syncService.loadDataSinceLastActivityDate();
            return ResponseEntity.ok("Refresh completed. " + count + " activities synced.");
        } catch (IllegalStateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
