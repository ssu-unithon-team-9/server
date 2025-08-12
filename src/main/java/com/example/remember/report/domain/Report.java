package com.example.remember.report.domain;

import com.example.remember.report.dto.response.ReportResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record Report(
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
        private ReportResponseDto.Total toTotalDto(Report.Total total) {
            return ReportResponseDto.Total.builder()
                    .danger(danger())
                    .weak(weak())
                    .summary(summary())
                    .build();
        }
    }

    @Builder
    public record Detail(
            String type,           // MEMORY | ATTENTION | LOGICAL
            int score,
            int scoreDifference,
            int location,
            int danger,
            String danger_level    // 서버에서 채울 예정이면 null
    ) {
    }
}
