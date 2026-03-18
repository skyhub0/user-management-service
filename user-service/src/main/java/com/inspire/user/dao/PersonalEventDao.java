package com.inspire.user.dao;

import com.inspire.userservice.mypage.domain.entity.PersonalEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PersonalEventDao extends JpaRepository<PersonalEventEntity, Long> {
    // 특정 유저의 해당 월 일정 가져오기
    List<PersonalEventEntity> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}