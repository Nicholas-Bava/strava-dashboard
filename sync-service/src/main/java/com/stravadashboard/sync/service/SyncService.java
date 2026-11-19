package com.stravadashboard.sync.service;

public interface SyncService {

    public int initialLoad(String after);
    public int loadDataSinceLastActivityDate();
    public void fetchAndStoreLaps(Long id);
}
