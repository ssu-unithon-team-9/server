package com.example.remember.RandomWordQuiz.controller;

import com.example.remember.RandomWordQuiz.dto.RandomWordRequest;
import com.example.remember.RandomWordQuiz.dto.RandomWordResponse;
import com.example.remember.RandomWordQuiz.service.RandomWordQuizService;
import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.quiz.QuizSuccessCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/words")
@RequiredArgsConstructor
@Tag(name = "단어 조합 퀴즈", description = "단어 조합 퀴즈 관련 API입니다.")
public class RandomWordQuizController {
    private final RandomWordQuizService randomWordQuizService;

    @GetMapping("/random")
    public ApiResponse<RandomWordResponse> randomByQuery(
            @RequestParam(defaultValue = "3") @Min(1) @Max(20) Integer length
    ) {
        RandomWordRequest randomWordRequest = new RandomWordRequest("ko", length, "noun");
        return ApiResponse.success(QuizSuccessCode.WORD_GENERATED, randomWordQuizService.generate(randomWordRequest));
    }
}