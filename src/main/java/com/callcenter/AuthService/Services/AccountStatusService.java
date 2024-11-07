package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Entities.AccountStatusEntity;
import com.callcenter.AuthService.Repositories.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusService
{
    private AccountStatusRepository accountStatusRepository;

    public AccountStatusService() {}

    @Autowired
    public AccountStatusService(AccountStatusRepository accountStatusRepository)
    {
        this.accountStatusRepository = accountStatusRepository;
    }

    public AccountStatusEntity getAccountStatusEntity(AccountStatusEnum accountStatusValue)
    {
        Optional<AccountStatusEntity> optionalAccountStatusEntity = accountStatusRepository.findByValue(accountStatusValue.getValue());
        if(optionalAccountStatusEntity.isEmpty())
        {
            return null;
        }

        return optionalAccountStatusEntity.get();
    }

    public AccountStatusEntity getAccountStatusEntity(String accountStatusValue)
    {
        Optional<AccountStatusEntity> optionalAccountStatusEntity = accountStatusRepository.findByValue(accountStatusValue);
        if(optionalAccountStatusEntity.isEmpty())
        {
            return null;
        }

        return optionalAccountStatusEntity.get();
    }


    public Integer getAccountStatusId(AccountStatusEnum accountStatusValue)
    {
        Optional<AccountStatusEntity> optionalAccountStatusEntity = accountStatusRepository.findByValue(accountStatusValue.getValue());
        if(optionalAccountStatusEntity.isEmpty())
        {
            return null;
        }

        return optionalAccountStatusEntity.get().getId();
    }

    public Integer getAccountStatusId(String accountStatusValue)
    {
        Optional<AccountStatusEntity> optionalAccountStatusEntity = accountStatusRepository.findByValue(accountStatusValue);
        if(optionalAccountStatusEntity.isEmpty())
        {
            return null;
        }

        return optionalAccountStatusEntity.get().getId();
    }
}
