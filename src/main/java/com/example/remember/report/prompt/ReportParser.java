package com.example.remember.report.prompt;

import com.example.remember.RandomWordQuiz.dto.RandomWordResponse;
import com.example.remember.common.exception.InternalServerException;
import com.example.remember.common.type.gpt.GPTErrorType;
import com.example.remember.common.type.quiz.QuizErrorCode;
import com.example.remember.common.type.report.ReportErrorType;
import com.example.remember.gpt.dto.GPTResponseDto;
import com.example.remember.report.dto.response.ReportResponseDto;
import com.example.remember.report.entity.ReportEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReportParser {
    private final ObjectMapper objectMapper;

    public ReportEntity toReportEntity(GPTResponseDto gptResponseDto) {
        String json = extractText(gptResponseDto);
        try {
            return objectMapper.readValue(json, ReportEntity.class);
        } catch (Exception e) {
            throw new InternalServerException(ReportErrorType.REPORT_CREATE_ERROR);
        }
    }

    public ReportResponseDto toReportResponse(GPTResponseDto gptResponseDto) {
        String json = extractText(gptResponseDto);
        try {
            return objectMapper.readValue(json, ReportResponseDto.class);
        } catch (Exception e) {
            throw new InternalServerException(ReportErrorType.REPORT_CREATE_ERROR);
        }
    }

    private String extractText(GPTResponseDto gptResponseDto) {
        List<GPTResponseDto.Out> outs = gptResponseDto.output();
        if (outs != null) {
            for (GPTResponseDto.Out out : outs) {
                if (out == null || out.content() == null) continue;
                for (GPTResponseDto.Content c : out.content()) {
                    if (c == null) continue;
                    String type = c.type();
                    if ("output_text".equals(type) || "text".equals(type)) {
                        String t = c.text();
                        if (t != null && !t.isBlank()) {
                            return t.trim();
                        }
                    }
                }
            }
        }
        throw new InternalServerException(GPTErrorType.GPT_RESPONSE_ERROR);
    }
}
