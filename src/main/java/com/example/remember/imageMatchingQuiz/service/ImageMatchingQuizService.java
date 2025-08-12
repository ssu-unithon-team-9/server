package com.example.remember.imageMatchingQuiz.service;

import com.example.remember.imageMatchingQuiz.dto.ImageMatchingQuizRequest;
import com.example.remember.imageMatchingQuiz.dto.ImageMatchingQuizResponse;

public interface ImageMatchingQuizService {
    ImageMatchingQuizResponse generate(ImageMatchingQuizRequest request);
}
