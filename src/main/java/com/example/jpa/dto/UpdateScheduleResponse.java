package com.example.jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {

    private final Long id;
    private final String title;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdateScheduleResponse(Long id, String title, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }
}
