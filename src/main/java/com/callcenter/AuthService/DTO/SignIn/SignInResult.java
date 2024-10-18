package com.callcenter.AuthService.DTO.SignIn;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class SignInResult
{
    private String accessToken;
    private Date accessTokenExpiration;
    private String refreshToken;
    private Date refreshTokenExpiration;
}
