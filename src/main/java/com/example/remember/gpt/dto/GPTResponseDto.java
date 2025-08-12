package com.example.remember.gpt.dto;

import java.util.List;

public record GPTResponseDto(
        String completion,
        String created,
        String id,
        String object,
        String output_text,
        List<Out> output) {
    public record Out(String id, String type, String role, List<Content> content) {
    }

    public record Content(String type, String text) {
    }
}
