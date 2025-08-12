package com.example.remember.s3.controller;

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
    public String uoploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = s3Service.upLoadImage(file);
            return "File uploaded successfully! imageUrl: " + imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }
}
