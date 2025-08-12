package com.example.remember.RandomWordQuiz.repository;

import com.example.remember.RandomWordQuiz.entity.WordGenerationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordGenerationLogRepository extends JpaRepository<WordGenerationLog, Long> {
}
