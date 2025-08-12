package com.example.remember.common.type.gpt;

import com.example.remember.common.type.ErrorType;

public enum GPTErrorType implements ErrorType {
    GPT_RESPONSE_ERROR(500, "Error parsing response from OpenAI Server")
    ;

    private final int code;
    private final String message;

    GPTErrorType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
