package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    //엔티티의 생성시간을 자동 관리
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
   //수정시간을 자동으로 관리해주는 어노테이션
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;


}
