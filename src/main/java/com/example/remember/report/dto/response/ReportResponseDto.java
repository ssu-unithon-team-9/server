package com.example.remember.report.dto.response;

import com.example.remember.report.domain.Report;
import lombok.Builder;

import java.util.List;

@Builder
public record ReportResponseDto(
        Long userId,
        String date,
        Total total,
        List<Detail> detail,
        String advice
) {
    @Builder
    public record Total(
            double score,
            String danger,
            List<String> weak,
            List<String> summary
    ) {
    }

    @Builder
    public record Detail(
            String type,
            int score,
            int scoreDifference,
            int location,
            int danger,
            String danger_level    // 서버에서 채울 예정이면 null
    ) {
    }

    public static ReportResponseDto toResponseDto(Report entity) {
        return ReportResponseDto.builder()
                .userId(entity.userId())
                .date(entity.date())
                .total(toTotalDto(entity.total()))
                .detail(entity.detail() != null
                        ? entity.detail().stream()
                        .map(ReportResponseDto::toDetailDto)
                        .toList()
                        : List.of())
                .advice(entity.advice())
                .build();
    }

    private static ReportResponseDto.Total toTotalDto(Report.Total total) {

        return ReportResponseDto.Total.builder()
                .score(total.score())
                .danger(total.danger())
                .weak(total.weak() != null ? List.copyOf(total.weak()) : List.of())
                .summary(total.summary() != null ? List.copyOf(total.summary()) : List.of())
                .build();
    }

    private static ReportResponseDto.Detail toDetailDto(Report.Detail detail) {
        if (detail == null) {
            return null;
        }

        return Detail.builder()
                .type(detail.type())
                .score(detail.score())
                .scoreDifference(detail.scoreDifference())
                .location(detail.location())
                .danger(detail.danger())
                .danger_level(detail.danger_level())
                .build();
    }
}
