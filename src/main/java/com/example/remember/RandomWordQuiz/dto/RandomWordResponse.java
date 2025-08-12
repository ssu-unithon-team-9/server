package com.example.remember.RandomWordQuiz.dto;

import java.util.List;

public record RandomWordResponse(
        List<String> words,
        String lang,
        Integer length,
        String pos
) {
}
