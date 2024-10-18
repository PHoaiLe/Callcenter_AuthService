package com.callcenter.AuthService.Services.Events;

import com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent.RefreshTokenEventProp;
import com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent.RefreshTokenRecordingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class AccountEventPublisher
{
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AccountEventPublisher(ApplicationEventPublisher applicationEventPublisher)
    {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(RefreshTokenEventProp prop)
    {
        RefreshTokenRecordingEvent event = RefreshTokenRecordingEvent.toEvent(this, prop);
        this.applicationEventPublisher.publishEvent(event);
    }

    public void publishEvent(Integer i)
    {

    }
}
