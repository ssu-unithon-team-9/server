package com.example.remember.RandomWordQuiz.service;

import com.example.remember.RandomWordQuiz.dto.RandomWordRequest;
import com.example.remember.RandomWordQuiz.dto.RandomWordResponse;

public interface RandomWordQuizService {
    RandomWordResponse generate(RandomWordRequest request);
}
