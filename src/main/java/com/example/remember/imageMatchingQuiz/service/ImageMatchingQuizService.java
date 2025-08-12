package com.example.remember.imageMatchingQuiz.service;

import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizRequest;
import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizResponse;

public interface ImageMatchingQuizService {
    imageMatchingQuizResponse generate(imageMatchingQuizRequest request);
}
