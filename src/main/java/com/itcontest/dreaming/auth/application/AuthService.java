package com.itcontest.dreaming.auth.application;

import com.itcontest.dreaming.auth.api.dto.response.UserInfo;

public interface AuthService {
    UserInfo getUserInfo(String authCode);
}
