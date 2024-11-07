package com.callcenter.AuthService.DTO.JWT;

import io.jsonwebtoken.Claims;

import java.util.Map;

public abstract class JwtClaimsProvider<T, KEY_TYPE>
{
    public static final String KEY = "UID";
    public static final String USER_ROLE = "USR";
    public static final String REGISTER_TYPE = "URT";
    public static final String NONCE = "NUO";


    protected JwtClaimsProvider(T value)
    {
        this.value = value;
    }

    private T value;

    protected T getValue()
    {
        return this.value;
    }

    public static JwtExtraClaims toJwtExtraClaims(Claims claims)
    {
        return JwtExtraClaims.builder()
                .key(claims.get(KEY, String.class))
                .role(claims.get(USER_ROLE, Integer.class))
                .registerType(claims.get(REGISTER_TYPE, Integer.class))
                .nonce(claims.get(NONCE, String.class))
                .build();
    }

    public static JwtFullClaims toJwtFullClaims(Claims claims)
    {
        return JwtFullClaims.builder()
                .key(claims.get(KEY, String.class))
                .role(claims.get(USER_ROLE, Integer.class))
                .registerType(claims.get(REGISTER_TYPE, Integer.class))
                .nonce(claims.get(NONCE, String.class))
                .issuedAt(claims.getIssuedAt())
                .expiration(claims.getExpiration())
                .build();
    }

    public abstract KEY_TYPE getKey();
    public abstract Map<String, Object> toMap();
}
