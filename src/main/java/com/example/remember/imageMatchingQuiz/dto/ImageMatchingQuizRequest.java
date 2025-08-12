package com.example.remember.imageMatchingQuiz.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ImageMatchingQuizRequest(
        @NotNull
        @Size(min = 3, max = 3)
        List<String> imageKeys
) {
}
