package com.example.remember.report.dto.response;

import com.example.remember.report.QuizType;
import lombok.Getter;

@Getter
public class ScoreResponseDto {
    private QuizType type;
    private Long score;
}
