package com.inspire.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspire.user.dao.UserRepository;
import com.inspire.user.domain.dto.UserRequestDTO;
import com.inspire.user.domain.dto.UserResponseDTO;
import com.inspire.user.domain.entity.UserEntity;

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
        
        return userRepository.findById(user)
                .map(UserResponseDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("유저 정보를 찾을 수 없습니다"));
    }
}