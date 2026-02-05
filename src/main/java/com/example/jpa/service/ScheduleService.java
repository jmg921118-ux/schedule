package com.example.jpa.service;

import com.example.jpa.dto.*;
import com.example.jpa.entity.Schedule;
import com.example.jpa.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //저장
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getWriter(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule); // User 정보 save 하기

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getWriter(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    //단건 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()

        );
    }

    //다건 조회
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetOneScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetOneScheduleResponse dto = new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;

    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );
        if (schedule.getPassword() == request.getPassword()) {
            schedule.update(
                    request.getTitle(),
                    request.getWriter()
            );

            return new UpdateScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
        throw new IllegalStateException("비밀번호 맞지 않습니다.");
    }


    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 유저입니다.")
        );

        if (request.getPassword() == schedule.getPassword()) {

            //유저가 있는 경우
            scheduleRepository.deleteAllById(scheduleId);
        }
        throw new IllegalStateException("비밀 번호가 맞지 않습니다.");
    }


}

