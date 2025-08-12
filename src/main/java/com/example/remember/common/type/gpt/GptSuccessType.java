package com.example.remember.common.type.gpt;

import com.example.remember.common.type.SuccessType;

public enum GptSuccessType implements SuccessType {
    GPT_SUCCESS(200, "테스트")
    ;

    GptSuccessType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
