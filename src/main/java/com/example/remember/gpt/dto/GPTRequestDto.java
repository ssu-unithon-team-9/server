package com.example.remember.gpt.dto;

public record GPTRequestDto(
        String model,
        String input,
        float temperature) {
}
