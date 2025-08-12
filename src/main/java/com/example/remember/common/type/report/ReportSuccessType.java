package com.example.remember.common.type.report;

import com.example.remember.common.type.SuccessType;

public enum ReportSuccessType implements SuccessType {
    SAVE_SCORE_SUCCESS(200, "점수를 저장하였습니다."),
    GET_REPORT_SUCCESS(200, "레포트를 성공적으로 불러왔습니다.");

    private final int code;
    private final String message;

    ReportSuccessType(int code, String message) {
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
