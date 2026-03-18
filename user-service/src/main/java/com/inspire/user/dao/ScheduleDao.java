package com.inspire.user.dao;

import com.inspire.userservice.mypage.domain.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleDao extends JpaRepository<ScheduleEntity, Long> {
    // 해당 월의 국가 시험 일정 가져오기
    List<ScheduleEntity> findByWrittenExamStartBetween(LocalDate startDate, LocalDate endDate);
}