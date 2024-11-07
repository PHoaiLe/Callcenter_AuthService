package com.callcenter.AuthService.DTO.JWT;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtExtraClaims
{
    private String key;
    private Integer role;
    private Integer registerType;
    private String nonce;
}
