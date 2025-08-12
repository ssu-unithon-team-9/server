package com.example.remember.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
public class S3Service {
    private final AmazonS3 amazonS3;

    private final String bucket = "memorlink-image";

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    // S3에 이미지 업로드
    public String upLoadImage(MultipartFile image) throws IOException {
        String originalFileName = image.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            originalFileName = "unnamed";
        }

        String extension = "";
        int lasDot = originalFileName.lastIndexOf('.');
        if (lasDot > 0) {
            extension = originalFileName.substring(lasDot).toLowerCase();
        }
        String fileName = UUID.randomUUID().toString() + extension;

        // 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(image.getContentType());
        metadata.setContentLength(image.getSize());

        // S3에 파일 업로드 요청
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, image.getInputStream(), metadata);

        // S3에 파일 업로드
        amazonS3.putObject(putObjectRequest);
        return getPublicUri(fileName);
    }

    private String getPublicUri(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, amazonS3.getRegionName(), fileName);
    }

    public String getUrl(String bucket, String key){
        URL url = amazonS3.getUrl(bucket, key);
        return url.toString();
    }
}
