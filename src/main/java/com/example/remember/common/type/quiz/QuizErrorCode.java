package com.example.remember.common.type.quiz;

import com.example.remember.common.type.ErrorType;

public enum QuizErrorCode implements ErrorType {
    WORD_GENERATION_FAILED(500, "서버 내부 오류입니다."),
    WORD_PARSING_FAILED(500, "GPT 응답 JSON 파싱 실패입니다."),
    WORD_TEXT_NOT_FOUND(500, "GPT 응답에서 텍스트를 찾을 수 없습니다");

    QuizErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
