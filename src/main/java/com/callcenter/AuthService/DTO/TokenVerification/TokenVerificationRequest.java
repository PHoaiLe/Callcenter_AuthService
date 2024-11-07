package com.callcenter.AuthService.DTO.TokenVerification;

public record TokenVerificationRequest (
        String token,
        String targetAPIMethod,
        String targetAPIPath
)
{ }
