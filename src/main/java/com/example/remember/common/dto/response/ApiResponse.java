package com.example.remember.common.dto.response;

import com.example.remember.common.exception.BaseException;
import com.example.remember.common.type.ErrorType;
import com.example.remember.common.type.SuccessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static ApiResponse success(SuccessType success) {
        return new ApiResponse(success.getCode(), success.getMessage());
    }

    public static <T> ApiResponse<T> success(SuccessType success, T data) {
        return new ApiResponse<T>(success.getCode(), success.getMessage(), data);
    }

    public static ApiResponse<?> error(BaseException exception) {
        ErrorType errorType = exception.getErrorType();
        return new ApiResponse<>(errorType.getCode(), errorType.getMessage(), null);
    }

    public static ApiResponse error(ErrorType error) {
        return new ApiResponse(error.getCode(), error.getMessage());
    }

    public static <T> ApiResponse<T> error(ErrorType error, T data) {
        return new ApiResponse<T>(error.getCode(), error.getMessage(), data);
    }
}
