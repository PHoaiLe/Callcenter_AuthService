package com.callcenter.AuthService.DTO.TokenVerification.ExternalOutput;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class TokenVerificationResponseBody
{
    private String key;
    private Integer role;
    private String targetPermission;

    public TokenVerificationResponseBody(String key, Integer role, String targetPermission) {
        this.key = key;
        this.role = role;
        this.targetPermission = targetPermission;
    }
}
