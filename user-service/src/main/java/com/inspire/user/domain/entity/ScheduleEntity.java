package com.inspire.user.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import java.time.LocalDate;

@Entity
@Table(name = "Schedule")
@Getter
public class ScheduleEntity {
    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "item_name")
    private String itemName;

    // 필기시험 시작일을 달력 표시용 대표 날짜로 사용
    @Column(name = "written_exam_start")