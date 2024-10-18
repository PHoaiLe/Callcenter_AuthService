package com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent;

import lombok.Builder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.util.Date;

@Getter
public class RefreshTokenRecordingEvent extends ApplicationEvent
{
    private String accountId;
    private String token;
    private Date issuedAt;
    private Date expiredAt;

    public static RefreshTokenRecordingEvent toEvent(Object source, RefreshTokenEventProp prop)
    {
        return new RefreshTokenRecordingEvent(source, prop.accountId(), prop.token(), prop.issuedAt(), prop.expiredAt());
    }

    public RefreshTokenRecordingEvent(Object source) {
        super(source);
    }

    public RefreshTokenRecordingEvent(Object source, String accountId, String token, Date issuedAt, Date expiredAt)
    {
        super(source);
        this.accountId = accountId;
        this.token = token;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }
}
