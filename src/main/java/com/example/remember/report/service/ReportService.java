package com.example.remember.report.service;

import com.example.remember.report.QuizType;
import com.example.remember.report.domain.Report;
import com.example.remember.report.dto.request.ScoreRequestDto;
import com.example.remember.report.dto.response.ReportResponseDto;

import java.util.Optional;

public interface ReportService {
    ReportResponseDto getReport(Long userId);

    void saveQuizScore(Long userId, QuizType type, int count, int totalCount);
}
