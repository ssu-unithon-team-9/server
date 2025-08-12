package com.example.remember.RandomWordQuiz.dto;

public record RandomWordRequest(
        String lang,
        int length,
        String pos
) {
}
