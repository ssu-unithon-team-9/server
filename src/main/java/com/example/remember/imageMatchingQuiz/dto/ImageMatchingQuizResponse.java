package com.example.remember.imageMatchingQuiz.dto;

import java.util.List;

public record ImageMatchingQuizResponse(
        List<ImageResource> images
) {
    public record ImageResource(
            String key,
            String url
    ) {
    }
}
