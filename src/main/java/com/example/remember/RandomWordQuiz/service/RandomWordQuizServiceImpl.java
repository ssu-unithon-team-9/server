package com.example.remember.RandomWordQuiz.service;

import com.example.remember.common.exception.BaseException;
import com.example.remember.common.exception.InternalServerException;
import com.example.remember.common.type.quiz.QuizErrorCode;
import com.example.remember.gpt.dto.GPTResponseDto;
import com.example.remember.gpt.service.ChatGPTService;
import com.example.remember.RandomWordQuiz.dto.RandomWordRequest;
import com.example.remember.RandomWordQuiz.dto.RandomWordResponse;
import com.example.remember.RandomWordQuiz.implement.GptRandomWordParser;
import com.example.remember.RandomWordQuiz.implement.PromptProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomWordQuizServiceImpl implements RandomWordQuizService {

    private final ChatGPTService chatGPTService;
    private final GptRandomWordParser gptRandomWordParser;

    @Override
    public RandomWordResponse generate(RandomWordRequest request) {
        final int maxRetry = 2;

        RandomWordResponse data = null;

        for (int attempt = 1; attempt <= maxRetry && data == null; attempt++) {
            try {
                String prompt = PromptProvider.jsonSingleWordPrompt(request, 10);
                GPTResponseDto responseDto = chatGPTService.chat(prompt);
                // GPT 응답에서 JSON 텍스트를 꺼내고 → 우리 도메인 DTO로 파싱
                data = gptRandomWordParser.toRandomWordResponse(responseDto);
            } catch (Exception e) {
                throw new InternalServerException(QuizErrorCode.WORD_GENERATION_FAILED);
            }
        }

        if (data == null) {
            throw new InternalServerException(QuizErrorCode.WORD_GENERATION_FAILED);
        }
        return new RandomWordResponse(
                data.words(),
                request.lang(),
                request.length(),
                request.pos()
        );
    }
}