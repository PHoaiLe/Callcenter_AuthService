package com.callcenter.AuthService.DTO.TokenVerification.ExternalInput;

public record TokenVerificationRequest (
        String token,
        String targetAPIMethod,
        String targetAPIPath
)
{ }
