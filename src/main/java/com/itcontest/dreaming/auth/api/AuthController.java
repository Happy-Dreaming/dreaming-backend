package com.itcontest.dreaming.auth.api;

import com.itcontest.dreaming.auth.api.dto.request.RefreshTokenReqDto;
import com.itcontest.dreaming.auth.api.dto.request.TokenReqDto;
import com.itcontest.dreaming.auth.api.dto.response.MemberLoginResDto;
import com.itcontest.dreaming.auth.api.dto.response.UserInfo;
import com.itcontest.dreaming.auth.application.AuthMemberService;
import com.itcontest.dreaming.auth.application.AuthService;
import com.itcontest.dreaming.auth.application.TokenService;
import com.itcontest.dreaming.global.jwt.api.dto.TokenDto;
import com.itcontest.dreaming.global.template.RspTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;
    private final AuthMemberService authMemberService;
    private final TokenService tokenService;

    public AuthController(AuthService authService, AuthMemberService authMemberService, TokenService tokenService) {
        this.authService = authService;
        this.authMemberService = authMemberService;
        this.tokenService = tokenService;
    }

    @Operation(summary = "로그인 후 토큰 발급", description = "액세스, 리프레쉬 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 발급 성공")
    })
    @PostMapping("/kakao/token")
    public RspTemplate<TokenDto> generateAccessAndRefreshToken(@RequestBody TokenReqDto tokenReqDto) {
        UserInfo userInfo = authService.getUserInfo(tokenReqDto.authCode());
        MemberLoginResDto getMemberDto = authMemberService.saveUserInfo(userInfo);
        TokenDto getToken = tokenService.getToken(getMemberDto);

        return new RspTemplate<>(HttpStatus.OK, "토큰 발급 성공", getToken);
    }

    @Operation(summary = "액세스 토큰 재발급", description = "리프레쉬 토큰으로 액세스 토큰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 발급 성공")
    })
    @PostMapping("/token/access")
    public RspTemplate<TokenDto> generateAccessToken(@RequestBody RefreshTokenReqDto refreshTokenReqDto) {
        TokenDto getToken = tokenService.generateAccessToken(refreshTokenReqDto);

        return new RspTemplate<>(HttpStatus.OK, "액세스 토큰 발급", getToken);
    }

}
