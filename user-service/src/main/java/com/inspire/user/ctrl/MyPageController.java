package com.inspire.user.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inspire.user.domain.dto.CalendarEventResponse;
import com.inspire.user.domain.dto.UserProfileResponse;
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

    // 2. 월별 일정 리스트 조회 (개인 일정 + 국가 시험)
    @GetMapping("/events")
    public ResponseEntity<List<CalendarEventResponse>> getMonthlyEvents(
            @RequestHeader("user-id") Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        
        List<CalendarEventResponse> response = myPageService.getMonthlyEvents(userId, year, month);
        return ResponseEntity.ok(response);
    }
}
