package com.inspire.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.user.dao.UserRepository;
import com.inspire.user.domain.entity.UserEntity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    // 1. 프로필 조회 (소개, 전화번호 제외)
    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileResponse.builder()
                .name(user.getName())
                .email(auth.getEmail())
                .avatar("") // 프론트엔드 목데이터에 맞춰 빈 문자열 전송
                .build();
    }
}