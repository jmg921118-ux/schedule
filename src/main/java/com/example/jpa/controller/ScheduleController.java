package com.example.jpa.controller;


import com.example.jpa.dto.*;
import com.example.jpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 유저 생성 정보를 가져옴
    @PostMapping("/jpas")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
       CreateScheduleResponse result = scheduleService.save(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/jpas/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
      GetOneScheduleResponse result = scheduleService.getOne(scheduleId);
      return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/jpas")
    public ResponseEntity<List<GetOneScheduleResponse>> getAllSchedules() {
       List<GetOneScheduleResponse> result = scheduleService.getAll();
       return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PutMapping("/jpas/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request) {
       UpdateScheduleResponse result = scheduleService.update(scheduleId, request);
       return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @DeleteMapping("/jpas/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request) {
        scheduleService.delete(scheduleId, request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
