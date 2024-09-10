package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.DTO.External.EmailPasswordRegisterInfoDTO;
import com.callcenter.AuthService.Entities.AccountEntity;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Repositories.AccountRepository;
import com.callcenter.AuthService.Repositories.EmailPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
{
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected EmailPasswordRepository emailPasswordRepository;

    public EmailPasswordAuthenticationEntity create(EmailPasswordRegisterInfoDTO providedInfo)
    {
        EmailPasswordAuthenticationEntity entity = EmailPasswordAuthenticationEntity.getInstance(providedInfo.email(), providedInfo.password());
        return emailPasswordRepository.save(entity);
    }
}
