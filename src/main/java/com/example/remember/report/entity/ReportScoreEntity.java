package com.example.remember.report.entity;

import com.example.remember.report.QuizType;
import com.example.remember.report.domain.Score;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "report_score")
public class ReportScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private QuizType type;

    @Column
    private Double score;

    @Builder
    public ReportScoreEntity(Long userId, QuizType type, Double score) {
        this.userId = userId;
        this.type = type;
        this.score = score;
    }

    public static ReportScoreEntity from(Score score) {
        return builder()
                .type(score.getType())
                .score(score.getScore())
                .build();
    }

    public Score toModel() {
        return Score.builder()
                .id(this.id)
                .type(this.type)
                .score(this.score)
                .build();
    }

    public void setScore(double score) {
        this.score = score;
    }
}
