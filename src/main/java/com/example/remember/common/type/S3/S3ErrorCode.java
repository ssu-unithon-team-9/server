package com.example.remember.common.type.S3;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.ErrorType;

public enum S3ErrorCode implements ErrorType {
    IMAGE_UPLOAD_FAILED(500, "이미지 업로드 실패"),
    IMAGE_LOAD_3_FAILED(400, "이미지 3개 가져오기 실패");

    private final int code;
    private final String message;

    S3ErrorCode(int code, String message) {
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
