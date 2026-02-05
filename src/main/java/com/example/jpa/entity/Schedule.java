package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "jpas")
@NoArgsConstructor(access = AccessLevel.PROTECTED)


public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    private String contents;
    private String writer;
    private String password;

    public Schedule(String title, String contents, String writer, String password) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;

    }

    public void update(String title, String writer) {
        this.title = title;
        this.writer = writer;

    }
}
