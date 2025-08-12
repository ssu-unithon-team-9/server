package com.example.remember.report.repository;

import com.example.remember.report.QuizType;
import com.example.remember.report.entity.ReportScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportScoreJpaRepository extends JpaRepository<ReportScoreEntity, Long> {
    Optional<ReportScoreEntity> findByType(QuizType type);
    Optional<ReportScoreEntity> findByUserIdAndType(Long userId, QuizType type);
}
