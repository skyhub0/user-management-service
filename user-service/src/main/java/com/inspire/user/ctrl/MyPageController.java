package com.inspire.user.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inspire.user.service.MyPageService;

import java.util.List;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    // 1. 유저 프로필 조회
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(@RequestHeader("user-id") Long userId) {
        // 실제 운영에서는 헤더나 JWT 토큰에서 userId를 추출하여 사용
        UserProfileResponse response = myPageService.getUserProfile(userId);
        return ResponseEntity.ok(response);
    }
}
