package com.example.remember.common.type.quiz;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.type.SuccessType;

public enum QuizSuccessCode implements SuccessType {
    WORD_GENERATED(200, "단어 생성 성공");

    private final int code;
    private final String message;

    QuizSuccessCode(int code, String message) {
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

    public <T>ApiResponse<T> toResponse(T data){
        return new ApiResponse<>(code, message, data);
    }
}
