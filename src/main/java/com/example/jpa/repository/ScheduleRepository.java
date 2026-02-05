package com.example.jpa.repository;

import com.example.jpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    void deleteAllById(Long userId);
}
