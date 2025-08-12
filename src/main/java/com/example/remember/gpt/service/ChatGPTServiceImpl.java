package com.example.remember.gpt.service;

import com.example.remember.gpt.dto.GPTRequestDto;
import com.example.remember.gpt.dto.GPTResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTServiceImpl implements ChatGPTService {
    @Qualifier("openaiRestTemplate")
    private final RestTemplate openaiRestTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.endpoint.url}")
    private String endpointUrl;

    @Override
    public GPTResponseDto chat(String prompt) {
        GPTRequestDto request = new GPTRequestDto(model, prompt, 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GPTRequestDto> entity = new HttpEntity<>(request, headers);

        GPTResponseDto gptResponse = openaiRestTemplate.exchange(
                endpointUrl, HttpMethod.POST, entity, GPTResponseDto.class).getBody();
        if (gptResponse != null) {
            return gptResponse;
        } else {
            throw new RuntimeException("Error parsing response from OpenAI Server");
        }
    }
}
