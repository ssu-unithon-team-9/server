package com.example.remember.common;

import com.example.remember.common.dto.response.ApiResponse;
import com.example.remember.common.exception.BaseException;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Hidden
public class GlobalExceptionAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(BaseException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ApiResponse.error(ex), ex.getHttpStatus());
    }
}