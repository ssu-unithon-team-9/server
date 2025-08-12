package com.example.remember.s3.controller;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.S3.S3ErrorCode;
import com.example.remember.common.type.S3.S3SuccessCode;
import com.example.remember.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("/upload")
    public ApiResponse<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = s3Service.upLoadImage(file);
            return ApiResponse.success(S3SuccessCode.IMAGE_UPLOAD, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error(S3ErrorCode.IMAGE_UPLOAD_FAILED, null);
        }
    }
}
