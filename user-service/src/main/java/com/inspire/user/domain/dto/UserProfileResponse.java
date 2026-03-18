package com.inspire.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfileResponse {
    private String name;
    private String email;
    private String avatar; // DB에 없다면 기본값 빈 문자열 처리
}