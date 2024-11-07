package com.callcenter.AuthService.DTO.TokenVerification;

import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class TokenVerificationResult
{
    private AccountEntity targetEntity;
    private Date issuedAt;
    private Date expiration;
    private String targetPermission;

    private TokenVerificationResult(AccountEntity targetEntity, Date issuedAt, Date expiration, String targetPermission) {
        this.targetEntity = targetEntity;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
        this.targetPermission = targetPermission;
    }
}
