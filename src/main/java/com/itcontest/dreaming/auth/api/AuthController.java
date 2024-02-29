package com.itcontest.dreaming.auth.api;

import com.itcontest.dreaming.auth.api.dto.request.TokenReqDto;
import com.itcontest.dreaming.auth.api.dto.response.UserInfo;
import com.itcontest.dreaming.auth.application.AuthService;
import com.itcontest.dreaming.global.template.RspTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/kakao/token")
    public RspTemplate<UserInfo> login(@RequestBody TokenReqDto tokenReqDto) {
        // userInfo
        UserInfo userInfo = authService.getUserInfo(tokenReqDto.authCode());

        // token 발급

        return new RspTemplate<>(HttpStatus.OK, "성공", userInfo);
    }
}
