package com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent;

import com.callcenter.AuthService.Entities.ProvidedRefreshTokenEntity;
import com.callcenter.AuthService.Repositories.ProvidedRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenRecordingEventListener implements ApplicationListener<RefreshTokenRecordingEvent>
{
    private ProvidedRefreshTokenRepository providedRefreshTokenRepository;

    @Autowired
    public RefreshTokenRecordingEventListener(ProvidedRefreshTokenRepository providedRefreshTokenRepository)
    {
        this.providedRefreshTokenRepository = providedRefreshTokenRepository;
    }

    @Override
    @Async
    public void onApplicationEvent(RefreshTokenRecordingEvent event) {
//        System.out.println(Thread.currentThread()); //apply asynchronous task successfully
//        System.out.println("Refresh token");

        //record the refresh token
        ProvidedRefreshTokenEntity providedRefreshTokenEntity = ProvidedRefreshTokenEntity.getInstance(event.getToken(),
                event.getAccountId(), event.getIssuedAt(), event.getExpiredAt());

        try
        {
            providedRefreshTokenRepository.save(providedRefreshTokenEntity);
        }
        catch (Exception exception)
        {
            //TODO: DO SOMETHINGS TO HANDLE THE ERROR HERE
            System.out.println(exception);
        }
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
