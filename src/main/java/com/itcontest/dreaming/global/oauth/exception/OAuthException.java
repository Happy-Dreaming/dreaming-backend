package com.itcontest.dreaming.global.oauth.exception;

public class OAuthException extends RuntimeException {
    public OAuthException(String message) {
        super(message);
    }

    public OAuthException() {
        this("OAuth 서버와의 통신 과정에서 문제가 발생했습니다.");
    }
}
