package com.example.remember.report.dto.request;

import com.example.remember.report.QuizType;
import lombok.Getter;

@Getter
public class ScoreRequestDto {
    private Long userId;
    private QuizType type;
    private int count;
    private int totalCount;
}
