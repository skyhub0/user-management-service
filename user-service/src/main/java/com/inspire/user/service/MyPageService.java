package com.inspire.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.user.dao.UserRepository;
import com.inspire.user.domain.dto.UserResponseDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MyPageService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDTO read(Long user) {
        System.out.println(">>>> User service read");
        
        // Auth 연동(통신)은 나중에 하므로 UserEntity만 조회해서 바로 DTO로 변환
        return userRepository.findById(user)
                .map(UserResponseDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("유저 정보를 찾을 수 없습니다"));
    }
}