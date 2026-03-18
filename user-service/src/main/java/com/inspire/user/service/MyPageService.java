package com.inspire.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.inspire.user.dao.AuthDao;
import com.inspire.user.dao.PersonalEventDao;
import com.inspire.user.dao.ScheduleDao;
import com.inspire.user.dao.UserDao;
import com.inspire.user.domain.dto.CalendarEventResponse;
import com.inspire.user.domain.dto.UserProfileResponse;
import com.inspire.user.domain.entity.AuthEntity;
import com.inspire.user.domain.entity.PersonalEventEntity;
import com.inspire.user.domain.entity.UserEntity;
import com.inspire.user.domain.entity.ScheduleEntity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserDao userDao;
    private final AuthDao authDao;
    private final PersonalEventDao personalEventDao;
    private final ScheduleDao scheduleDao;

    // 1. 프로필 조회 (소개, 전화번호 제외)
    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        AuthEntity auth = authDao.findById(userId).orElseThrow(() -> new RuntimeException("Auth not found"));

        return UserProfileResponse.builder()
                .name(user.getName())
                .email(auth.getEmail())
                .avatar("") // 프론트엔드 목데이터에 맞춰 빈 문자열 전송
                .build();
    }

    // 2. 이번 달 일정 통합 조회 (개인 일정 + 국가 시험 일정)
    public List<CalendarEventResponse> getMonthlyEvents(Long userId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<CalendarEventResponse> allEvents = new ArrayList<>();

        // 개인 일정 가져오기
        List<PersonalEventEntity> personalEvents = personalEventDao.findByUserIdAndDateBetween(userId, startDate, endDate);
        for (PersonalEventEntity pe : personalEvents) {
            allEvents.add(CalendarEventResponse.builder()
                    .id("P" + pe.getPersonalEventId()) // 개인 일정 식별자 P 추가
                    .title(pe.getTitle())
                    .date(pe.getDate().toString())
                    .type(pe.getType())
                    .description(pe.getDescription())
                    .build());
        }

        // 국가 시험 일정 가져오기
        List<ScheduleEntity> nationalSchedules = scheduleDao.findByWrittenExamStartBetween(startDate, endDate);
        for (ScheduleEntity sc : nationalSchedules) {
            allEvents.add(CalendarEventResponse.builder()
                    .id("S" + sc.getScheduleId()) // 국가 시험 식별자 S 추가
                    .title(sc.getItemName())
                    .date(sc.getWrittenExamStart().toString())
                    .type("exam") // 국가 시험은 무조건 'exam' 타입으로 프론트에 전달
                    .description("국가 시험 일정")
                    .build());
        }

        return allEvents;
    }

    // 3. 특정 상세 일정 조회 (달력 하단 클릭 시)
    public CalendarEventResponse getEventDetail(String eventId) {
        // 프론트엔드가 월별 전체 일정을 이미 가지고 있어서 자체 필터링을 할 수도 있지만,
        // 필요하다면 단건 조회 로직을 여기에 구현 (식별자 P/S로 구분하여 조회)
        // 현재 리액트 코드는 프론트엔드 상태값(eventsOnDate)으로 상세를 보여주므로 
        // 굳이 백엔드 단건 호출이 필수적이지 않게 잘 설계되어 있습니다.
        return null; 
    }
}