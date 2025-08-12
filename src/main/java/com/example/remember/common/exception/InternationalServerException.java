package com.example.remember.common.exception;

import com.example.remember.common.type.ErrorType;
import org.springframework.http.HttpStatus;

public class InternationalServerException extends BaseException{
    public InternationalServerException(ErrorType errorType) {
        super(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
