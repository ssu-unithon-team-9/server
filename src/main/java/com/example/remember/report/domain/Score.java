package com.example.remember.report.domain;

import com.example.remember.report.QuizType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Score {
    private Long id;
    private QuizType type;
    private Double score;

    @Builder
    public Score(Long id, QuizType type, Double score) {
        this.id = id;
        this.type = type;
        this.score = score;
    }
}
