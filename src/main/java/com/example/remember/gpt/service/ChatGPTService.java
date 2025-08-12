package com.example.remember.gpt.service;

import com.example.remember.gpt.dto.GPTResponseDto;

public interface ChatGPTService {
    GPTResponseDto chat(String prompt);
}
