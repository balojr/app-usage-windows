package com.example.demo.service;

import com.example.demo.domain.ScreentimeEntry;
import com.example.demo.repository.ScreentimeRepository;
import com.example.demo.util.WindowsUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ScreentimeService {
    private final ScreentimeRepository repository;
    private final Map<String, Long> screentimeCache = new ConcurrentHashMap<>();
    private String lastApp = "Unknown";
    private long lastCheckTime = System.currentTimeMillis();

    public ScreentimeService(ScreentimeRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        // Load existing data from DB into cache
        repository.findAll().forEach(entry ->
                screentimeCache.put(entry.getApplication(), entry.getTotalMillis()));
        trackScreentime();
    }

    @Scheduled(fixedRate = 1000)
    public void trackScreentime() {
        String currentApp = WindowsUtils.getActiveApplication();
        long currentTime = System.currentTimeMillis();

        if (!lastApp.equals("Unknown")) {
            long timeSpent = currentTime - lastCheckTime;
            screentimeCache.put(lastApp, screentimeCache.getOrDefault(lastApp, 0L) + timeSpent);
            repository.save(new ScreentimeEntry(lastApp, screentimeCache.get(lastApp)));
        }

        lastApp = currentApp;
        lastCheckTime = currentTime;
    }

    public Map<String, Long> getScreentimeMetrics() {
        return new ConcurrentHashMap<>(screentimeCache);
    }
}
