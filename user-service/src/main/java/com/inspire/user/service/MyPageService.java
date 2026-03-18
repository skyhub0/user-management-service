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

    // 1. 프로필 조회 (소개, 전화번호 제외)
    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileResponse.builder()
                .name(user.getName())
                .email(auth.getEmail())
                .avatar("") // 프론트엔드 목데이터에 맞춰 빈 문자열 전송
                .build();
    }
}