package com.inspire.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CalendarEventResponse {
    private String id;          // 프론트에서 string으로 요구함 ('1', '2' 등)
    private String title;
    private String date;        // 'YYYY-MM-DD' 형식
    private String type;        // 'study', 'exam', 'deadline', 'other'
    private String description;
}