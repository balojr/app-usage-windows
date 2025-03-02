package com.example.demo.web.rest;

import com.example.demo.service.ScreentimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class ScreentimeController {
    private final ScreentimeService screentimeService;

    public ScreentimeController(ScreentimeService screentimeService) {
        this.screentimeService = screentimeService;
    }

    @GetMapping("/")
    public String getScreentime(Model model) {
        Map<String, Long> rawMetrics = screentimeService.getScreentimeMetrics();
        Map<String, String> formattedMetrics = new java.util.HashMap<>();
        rawMetrics.forEach((app, millis) -> {
            long seconds = millis / 1000;
            String time = String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
            formattedMetrics.put(app, time);
        });
        model.addAttribute("screentime", formattedMetrics);
        return "screentime";
    }

//    @GetMapping("/screentime")
//    public Map<String, String> getScreentime() {
//        Map<String, Long> rawMetrics = screentimeService.getScreentimeMetrics();
//        Map<String, String> formattedMetrics = new ConcurrentHashMap<>();
//        rawMetrics.forEach((app, millis) -> {
//            long seconds = millis / 1000;
//            String time = String.format("%d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60);
//            formattedMetrics.put(app, time);
//        });
//        return formattedMetrics;
//    }
}
