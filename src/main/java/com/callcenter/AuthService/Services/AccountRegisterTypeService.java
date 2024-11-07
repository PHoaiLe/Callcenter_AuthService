package com.callcenter.AuthService.Services;


import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Entities.RegisterTypeEntity;
import com.callcenter.AuthService.Repositories.RegisterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountRegisterTypeService
{
    private RegisterTypeRepository registerTypeRepository;

    public AccountRegisterTypeService() {}

    @Autowired
    public AccountRegisterTypeService(RegisterTypeRepository registerTypeRepository)
    {
        this.registerTypeRepository = registerTypeRepository;
    }

    public RegisterTypeEntity getRegisterTypeEntity(AccountRegisterTypeEnum accountRegisterTypeEnum)
    {
        Optional<RegisterTypeEntity> optionalRegisterTypeEntity = registerTypeRepository.findByValue(accountRegisterTypeEnum.getValue());
        if(optionalRegisterTypeEntity.isEmpty())
        {
            return null;
        }

        return optionalRegisterTypeEntity.get();
    }

    public RegisterTypeEntity getRegisterTypeEntity(String registerValue)
    {
        Optional<RegisterTypeEntity> optionalRegisterTypeEntity = registerTypeRepository.findByValue(registerValue);
        if(optionalRegisterTypeEntity.isEmpty())
        {
            return null;
        }

        return optionalRegisterTypeEntity.get();
    }

    public Integer getRegisterTypeId(AccountRegisterTypeEnum accountRegisterTypeEnum)
    {
        Optional<RegisterTypeEntity> optionalRegisterTypeEntity = registerTypeRepository.findByValue(accountRegisterTypeEnum.getValue());
        if(optionalRegisterTypeEntity.isEmpty())
        {
            return null;
        }

        return optionalRegisterTypeEntity.get().getId();
    }

    public Integer getRegisterTypeId(String registerValue)
    {
        Optional<RegisterTypeEntity> optionalRegisterTypeEntity = registerTypeRepository.findByValue(registerValue);
        if(optionalRegisterTypeEntity.isEmpty())
        {
            return null;
        }

        return optionalRegisterTypeEntity.get().getId();
    }

}
