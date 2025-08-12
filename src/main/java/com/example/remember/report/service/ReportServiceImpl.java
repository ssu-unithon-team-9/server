package com.example.remember.report.service;

import com.example.remember.common.exception.BadRequestException;
import com.example.remember.common.exception.InternalServerException;
import com.example.remember.common.type.quiz.QuizErrorCode;
import com.example.remember.common.type.report.ReportErrorType;
import com.example.remember.gpt.dto.GPTResponseDto;
import com.example.remember.gpt.service.ChatGPTService;
import com.example.remember.report.QuizType;
import com.example.remember.report.dto.response.ReportResponseDto;
import com.example.remember.report.entity.ReportEntity;
import com.example.remember.report.entity.ReportScoreEntity;
import com.example.remember.report.prompt.PromptProvider;
import com.example.remember.report.prompt.ReportParser;
import com.example.remember.report.repository.ReportJpaRepository;
import com.example.remember.report.repository.ReportScoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ChatGPTService chatGPTService;
    private final ReportJpaRepository reportJpaRepository;
    private final ReportScoreJpaRepository reportScoreJpaRepository;
    private final ReportParser reportParser;

    @Override
    @Transactional
    public ReportResponseDto getReport(Long userId) {
        double memoryScore = 0;
        double attentionScore = 0;
        double logicalScore = 0;

        memoryScore = reportScoreJpaRepository.findByUserIdAndType(userId, QuizType.MEMORY)
                .orElseThrow(() -> new BadRequestException(ReportErrorType.QUIZ_NOT_DONE_ERROR))
                .getScore();

        attentionScore = reportScoreJpaRepository.findByUserIdAndType(userId,QuizType.ATTENTION)
                .orElseThrow(() -> new BadRequestException(ReportErrorType.QUIZ_NOT_DONE_ERROR))
                .getScore();

        logicalScore = reportScoreJpaRepository.findByUserIdAndType(userId,QuizType.LOGICAL)
                .orElseThrow(() -> new BadRequestException(ReportErrorType.QUIZ_NOT_DONE_ERROR))
                .getScore();

        Optional<ReportResponseDto> report = reportJpaRepository
                .findByUserId(userId)
                .map(ReportEntity::toDomain)
                .map(ReportResponseDto::toResponseDto);

        if (report.isPresent()) {
            return report.orElseThrow(() -> new InternalServerException(ReportErrorType.REPORT_CREATE_ERROR));
        } else {
            ReportResponseDto data = null;

            for (int attempt = 1; attempt <= 2 && data == null; attempt++) {
                try {
                    String prompt = PromptProvider.jsonSingleWordPrompt(memoryScore, attentionScore, logicalScore);
                    GPTResponseDto responseDto = chatGPTService.chat(prompt);

                    data = reportParser.toReportResponse(responseDto);
                    data = ReportResponseDto.builder()
                            .userId(userId)
                            .date(data.date())
                            .total(data.total())
                            .detail(data.detail())
                            .advice(data.advice())
                            .build();

                    saveReport(userId, data);
                } catch (Exception e) {
                    throw new InternalServerException(QuizErrorCode.WORD_GENERATION_FAILED);
                }
            }

            return data;
        }
    }

    @Transactional
    public Long saveReport(Long userId, ReportResponseDto dto) {
        ReportEntity report = ReportEntity.builder()
                .userId(userId)
                .date(dto.date())
                .total(ReportEntity.Total.builder()
                        .score(dto.total().score())
                        .danger(dto.total().danger())
                        // goal 안씀
                        .weak(dto.total().weak())
                        .summary(dto.total().summary())
                        .build())
                .advice(dto.advice())
                .build();

        // detail 추가 (danger_level -> dangerLevel 매핑 주의)
        for (ReportResponseDto.Detail d : dto.detail()) {
            ReportEntity.Detail child = ReportEntity.Detail.builder()
                    .type(d.type())
                    .score(d.score())
                    .scoreDifference(d.scoreDifference())
                    .location(d.location())
                    .danger(d.danger())
                    .dangerLevel(d.danger_level()) // ★ 문자열 그대로 저장
                    .build();
            report.addDetail(child); // ★ 여기서 child.setReport(report)까지 함께 수행
        }

        reportJpaRepository.save(report); // CascadeType.ALL이면 detail도 함께 저장
        return report.getId();
    }

    @Override
    @Transactional
    public void saveQuizScore(Long userId, QuizType type, int count, int totalCount) {
        double quizScore;
        int tenPercentCount = 9;

        if (type == QuizType.MEMORY) {
            quizScore = 100 * (0.3 * ((double) count / totalCount) + 0.7 * Math.min(1, ((double) count / tenPercentCount)));
        } else if (type == QuizType.ATTENTION) {
            quizScore = ((double) count / totalCount) * 100;
        } else if (type == QuizType.LOGICAL) {
            quizScore = 100 * (0.3 + 0.7 * (Math.min(1, ((double) count / tenPercentCount))));
        } else {
            quizScore = 0;
        }

        ReportScoreEntity entity = reportScoreJpaRepository.findByType(type)
                .map(e -> {
                    e.setScore(quizScore);
                    return e;
                })
                .orElseGet(() ->
                        ReportScoreEntity.builder()
                                .userId(userId)
                                .type(type)
                                .score(quizScore)
                                .build()
                );

        reportScoreJpaRepository.save(entity);
    }
}
