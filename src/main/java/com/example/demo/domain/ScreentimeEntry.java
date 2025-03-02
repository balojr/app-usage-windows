package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ScreentimeEntry {
    @Id
    private String application;
    private long totalMillis;


    public ScreentimeEntry() {}

    public ScreentimeEntry(String application, long totalMillis) {
        this.application = application;
        this.totalMillis = totalMillis;
    }
}
