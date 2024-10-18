package com.callcenter.AuthService.DTO.JWT;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Builder
@Data
@Getter
public class JwtGeneratedToken
{
    private String token;
    private Date issuedAt;
    private Date expiredAt;
}
