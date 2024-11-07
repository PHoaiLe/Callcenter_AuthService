package com.callcenter.AuthService.Services.Events.RefreshTokenRecordingEvent;

import com.callcenter.AuthService.Entities.ProvidedRefreshTokenEntity;
import com.callcenter.AuthService.Repositories.ProvidedRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenRecordingEventListener implements ApplicationListener<RefreshTokenRecordingEvent>
{
    private ProvidedRefreshTokenRepository providedRefreshTokenRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RefreshTokenRecordingEventListener(ProvidedRefreshTokenRepository providedRefreshTokenRepository, PasswordEncoder passwordEncoder)
    {
        this.providedRefreshTokenRepository = providedRefreshTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Async
    public void onApplicationEvent(RefreshTokenRecordingEvent event) {
//        System.out.println(Thread.currentThread()); //apply asynchronous task successfully
//        System.out.println("Refresh token");

        //hashing token before storing it
        String hashedToken = passwordEncoder.encode(event.getToken());
        //record the refresh token
        ProvidedRefreshTokenEntity providedRefreshTokenEntity = ProvidedRefreshTokenEntity.getInstance(hashedToken,
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
