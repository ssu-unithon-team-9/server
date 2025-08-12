package com.example.remember.gpt.service;

import com.example.remember.gpt.dto.GPTResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ChatGPTService {
    GPTResponseDto chat(String prompt);
}
