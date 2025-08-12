package com.example.remember.imageMatchingQuiz.controller;

import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizRequest;
import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizResponse;
import com.example.remember.imageMatchingQuiz.service.ImageMatchingQuizService;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz/image-matching")
@RequiredArgsConstructor
public class ImageMatchingQuizController {
    private final ImageMatchingQuizService imageMatchingQuizService;

    @GetMapping("/url")
    public ResponseEntity<imageMatchingQuizResponse> getPresignedUrls(
            @RequestParam("key")
            @Size(min = 3, max = 3)
            List<String> keys) {
        var normalized = keys.stream().map(this::toKey).toList();
        var resp = imageMatchingQuizService.generate(new imageMatchingQuizRequest(normalized));
        return ResponseEntity.ok(resp);
    }

    private String toKey(String urlOrKey) {
        if (urlOrKey == null)
            return null;
        if (urlOrKey.startsWith("http://") || urlOrKey.startsWith("https://")) {
            String path = URI.create(urlOrKey).getPath();
            return path != null ? path.replaceFirst("^/", "") : urlOrKey;
        }
        return urlOrKey;
    }
}
