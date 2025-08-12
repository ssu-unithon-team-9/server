package com.example.remember.imageMatchingQuiz.service;

import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizRequest;
import com.example.remember.imageMatchingQuiz.dto.imageMatchingQuizResponse;
import com.example.remember.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageMatchingQuizServiceImpl implements ImageMatchingQuizService {
    private final S3Service s3Service;
    private final String bucket = "memorlink-image";

    @Override
    public imageMatchingQuizResponse generate(imageMatchingQuizRequest request) {
        List<String> keys = request.imageKeys();
        if (keys == null || keys.size() != 3) {
            throw new IllegalArgumentException("이미지 키의 개수가 3개가 아닙니다.");
        }

        var images = keys.stream()
                .map(k -> new imageMatchingQuizResponse.ImageResource(
                        k,
                        s3Service.getUrl(bucket, k)
                ))
                .toList();
        return new imageMatchingQuizResponse(images);
    }
}
