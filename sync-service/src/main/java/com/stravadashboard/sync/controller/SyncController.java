package com.stravadashboard.sync.controller;

import com.stravadashboard.sync.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    @PostMapping("/sync/activities/{id}/laps")
    public ResponseEntity<String> fetchAndStoreLaps(@PathVariable Long id){
        try {
            syncService.fetchAndStoreLaps(id);
            return ResponseEntity.ok("Lap fetch complete for activity " + id);
        } catch (Exception e) {
            log.error("Lap fetch failed for activity {}", id, e);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Error occurred while fetching laps for activity " + id);
        }
    }
}
