package com.example.remember.gpt.dto;

import java.util.List;

public record GPTRequestDto(
        String model,
        String input,
        float temperature) {
}
