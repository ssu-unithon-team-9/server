package com.example.remember.common.type.S3;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.SuccessType;

public enum S3SuccessCode implements SuccessType {
    IMAGE_UPLOAD(200, "이미지 업로드 성공");

    private final int code;
    private final String message;

    S3SuccessCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public <T> ApiResponse<T> toResponse(T data) {
        return new ApiResponse<>(code, message, data);
    }
}
