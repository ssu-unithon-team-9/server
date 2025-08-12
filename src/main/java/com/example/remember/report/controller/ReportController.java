package com.example.remember.report.controller;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.report.ReportSuccessType;
import com.example.remember.report.dto.request.ScoreRequestDto;
import com.example.remember.report.dto.response.ReportResponseDto;
import com.example.remember.report.entity.ReportScoreEntity;
import com.example.remember.report.repository.ReportScoreJpaRepository;
import com.example.remember.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
@Tag(name = "REPORT", description = "report 관련 api입니다.")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/score")
    @Operation(summary = "성적 저장 API", description = "성적을 저장합니다. 성적은 최종 퀴즈를 반영합니다.")
    public ApiResponse score(
            @RequestBody ScoreRequestDto scoreRequestDto
    ) {
        reportService.saveQuizScore(
                scoreRequestDto.getUserId(),
                scoreRequestDto.getType(),
                scoreRequestDto.getCount(),
                scoreRequestDto.getTotalCount()
        );
        return ApiResponse.success(ReportSuccessType.SAVE_SCORE_SUCCESS);
    }

    @GetMapping("")
    @Operation(summary = "report 호출 API", description = "report를 확인합니다.")
    public ApiResponse<ReportResponseDto> report(
            @RequestParam(name = "userId") Long userId
    ) {
        return ApiResponse.success(ReportSuccessType.GET_REPORT_SUCCESS, reportService.getReport(userId));
    }
}
