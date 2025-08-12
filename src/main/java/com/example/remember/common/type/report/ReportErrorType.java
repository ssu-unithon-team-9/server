package com.example.remember.common.type.report;

import com.example.remember.common.type.ErrorType;

public enum ReportErrorType implements ErrorType {
    REPORT_CREATE_ERROR(500, "레포트를 생성할 수 없습니다."),
    QUIZ_NOT_DONE_ERROR(400, "퀴즈를 전부 풀어야합니다.");

    private final int code;
    private final String message;

    ReportErrorType(int code, String message) {
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
}
