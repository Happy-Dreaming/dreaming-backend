package com.itcontest.dreaming.global.error.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}