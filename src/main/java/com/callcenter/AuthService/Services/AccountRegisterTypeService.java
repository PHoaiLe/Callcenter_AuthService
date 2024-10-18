package com.callcenter.AuthService.Services;


import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Entities.RegisterTypeEntity;
import com.callcenter.AuthService.Repositories.RegisterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountRegisterTypeService
{
    private RegisterTypeRepository registerTypeRepository;
    private HashMap<String, RegisterTypeEntity> mapOfRegisterTypes;

    public AccountRegisterTypeService() {}

    @Autowired
    public AccountRegisterTypeService(RegisterTypeRepository registerTypeRepository)
    {
        this.registerTypeRepository = registerTypeRepository;

        initialize();
    }

    private void initialize()
    {
        HashMap<String, RegisterTypeEntity> mapOfRegisterTypes = new HashMap<>();

        List<RegisterTypeEntity> listOfEntities = registerTypeRepository.findAll();

        for(RegisterTypeEntity entity: listOfEntities)
        {
            mapOfRegisterTypes.put(entity.getValue(), entity);
        }

        this.mapOfRegisterTypes = mapOfRegisterTypes;
    }

    public RegisterTypeEntity getRegisterTypeEntity(AccountRegisterTypeEnum accountRegisterTypeEnum)
    {
        return this.mapOfRegisterTypes.get(accountRegisterTypeEnum.getValue());
    }

    public RegisterTypeEntity getRegisterTypeEntity(String registerValue)
    {
        return this.mapOfRegisterTypes.get(registerValue);
    }

    public Integer getRegisterTypeId(AccountRegisterTypeEnum accountRegisterTypeEnum)
    {
        RegisterTypeEntity entity = this.mapOfRegisterTypes.get(accountRegisterTypeEnum.getValue());
        if(entity == null)
        {
            return null;
        }

        return entity.getId();
    }

    public Integer getRegisterTypeId(String registerValue)
    {
        RegisterTypeEntity entity = this.mapOfRegisterTypes.get(registerValue);
        if(entity == null)
        {
            return null;
        }
        return entity.getId();
    }

}
