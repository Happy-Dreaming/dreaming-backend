package com.itcontest.dreaming.auth.exception;

import com.itcontest.dreaming.global.error.exception.InvalidGroupException;

public class InvalidTokenException extends InvalidGroupException {
    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException() {
        this("토큰이 유효하지 않습니다.");
    }
}
