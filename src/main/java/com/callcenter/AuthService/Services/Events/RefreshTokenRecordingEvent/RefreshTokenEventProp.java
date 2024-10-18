package com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent;

import java.util.Date;

public record RefreshTokenEventProp(
        String accountId,
        String token,
        Date issuedAt,
        Date expiredAt
)
{}
