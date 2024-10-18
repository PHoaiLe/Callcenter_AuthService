package com.callcenter.AuthService.DTO.JWT;

import java.util.Map;

public abstract class JwtExtraClaims<T, KEY_TYPE>
{
    public static final String ACCOUNT_ID = "AID";
    public static final String USER_ROLE = "USR";
    public static final String REGISTER_TYPE = "URT";
    public static final String NONCE = "NUO";


    protected JwtExtraClaims(T value)
    {
        this.value = value;
    }

    private T value;

    protected T getValue()
    {
        return this.value;
    }

    public abstract KEY_TYPE getKey();
    public abstract Map<String, Object> toMap();
}
