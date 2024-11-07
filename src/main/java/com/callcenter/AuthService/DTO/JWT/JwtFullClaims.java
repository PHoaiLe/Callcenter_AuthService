package com.callcenter.AuthService.DTO.JWT;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class JwtFullClaims
{
    private String key;
    private Integer role;
    private Integer registerType;
    private String nonce;

    private Date issuedAt;
    private Date expiration;

    @Override
    public String toString() {
        return "JwtFullClaims{" +
                "key='" + key + '\'' +
                ", role=" + role +
                ", registerType=" + registerType +
                ", nonce='" + nonce + '\'' +
                ", issuedAt=" + issuedAt +
                ", expiration=" + expiration +
                '}';
    }
}
