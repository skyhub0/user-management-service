package com.inspire.user.domain.dto;

import com.inspire.user.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String name;
    // 통신 연동 전이므로 이메일 정보는 임시로 제외

    public static UserResponseDTO fromEntity(UserEntity entity) {
        return UserResponseDTO.builder()
                .name(entity.getName())
                .build();
    }
}