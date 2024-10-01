package com.callcenter.AuthService.Services.AccountStatusService;

import com.callcenter.AuthService.Constants.AccountStatusEnum;
import com.callcenter.AuthService.Entities.AccountStatusEntity;
import com.callcenter.AuthService.Repositories.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountStatusService
{
    private AccountStatusRepository accountStatusRepository;
    private HashMap<String, AccountStatusEntity> mapOfAccountStatus;

    public AccountStatusService() {}

    @Autowired
    public AccountStatusService(AccountStatusRepository accountStatusRepository)
    {
        this.accountStatusRepository = accountStatusRepository;

        initialize();
    }

    private void initialize()
    {
        HashMap<String, AccountStatusEntity> mapOfAccountStatus = new HashMap<>();

        List<AccountStatusEntity> listOfEntities = accountStatusRepository.findAll();

        for (AccountStatusEntity entity : listOfEntities)
        {
            mapOfAccountStatus.put(entity.getValue(), entity);
        }

        this.mapOfAccountStatus = mapOfAccountStatus;
    }

    public AccountStatusEntity getAccountStatusEntity(AccountStatusEnum accountStatusValue)
    {
        return this.mapOfAccountStatus.get(accountStatusValue.getValue());
    }

    public AccountStatusEntity getAccountStatusEntity(String accountStatusValue)
    {
        return this.mapOfAccountStatus.get(accountStatusValue);
    }


    public Integer getAccountStatusId(AccountStatusEnum accountStatusValue)
    {
        AccountStatusEntity entity = this.mapOfAccountStatus.get(accountStatusValue.getValue());
        if(entity == null)
        {
            return null;
        }

        return entity.getId();
    }

    public Integer getAccountStatusId(String accountStatusValue)
    {
        AccountStatusEntity entity = this.mapOfAccountStatus.get(accountStatusValue);
        if(entity == null)
        {
            return null;
        }

        return entity.getId();
    }
}
