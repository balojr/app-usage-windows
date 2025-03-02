package com.example.demo.repository;

import com.example.demo.domain.ScreentimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreentimeRepository extends JpaRepository<ScreentimeEntry, String> {
}
