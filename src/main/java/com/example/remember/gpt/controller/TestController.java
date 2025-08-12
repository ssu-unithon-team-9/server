package com.example.remember.gpt.controller;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.gpt.GptSuccessType;
import com.example.remember.gpt.dto.GPTResponseDto;
import com.example.remember.gpt.service.ChatGPTServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gpt")
@RequiredArgsConstructor
@Tag(name = "GPT", description = "GPT test용입니다.")
public class TestController {

    private final ChatGPTServiceImpl chatGPTService;

    @GetMapping("")
    @Operation(summary = "GPT 호출 API", description = "prompt 입력 시 gpt가 답변해줍니다.")
    public ApiResponse<GPTResponseDto> chat(
            @NotNull @RequestParam("prompt") String prompt
    ) {
        return ApiResponse.success(GptSuccessType.GPT_SUCCESS, chatGPTService.chat(prompt));
    }
}