package com.example.remember.RandomWordQuiz.implement;

import com.example.remember.common.exception.InternationalServerException;
import com.example.remember.common.type.quiz.QuizErrorCode;
import com.example.remember.gpt.dto.GPTResponseDto;
import com.example.remember.RandomWordQuiz.dto.RandomWordResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GptRandomWordParser {
    private final ObjectMapper objectMapper;

    public RandomWordResponse toRandomWordResponse(GPTResponseDto gptResponseDto) {
        String json = extractText(gptResponseDto);
        System.out.println(json);
        try {
            return objectMapper.readValue(json, RandomWordResponse.class);
        } catch (Exception e) {
            throw new InternationalServerException(QuizErrorCode.WORD_PARSING_FAILED);
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
        throw new InternationalServerException(QuizErrorCode.WORD_GENERATION_FAILED);
    }
}
