package com.itcontest.dreaming.auth.api.dto.response;

public record UserInfo(
        String email,
        String nickname,
        String picture
) {
}
