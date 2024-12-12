package com.callcenter.AuthService.DTO.RefreshToken;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class RefreshTokenResponseBody
{
    private String accessToken;
    private Date accessTokenExpiration;
    private String refreshToken;
    private Date refreshTokenExpiration;
}
