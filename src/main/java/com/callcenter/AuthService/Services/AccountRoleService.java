package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Entities.RoleEntity;
import com.callcenter.AuthService.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AccountRoleService
{
    private RoleRepository roleRepository;
    private HashMap<String, RoleEntity> mapOfRoles;

    public AccountRoleService() {}

    @Autowired
    public AccountRoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;

        initialize();
    }

    private void initialize()
    {
        HashMap<String, RoleEntity> mapOfRoles = new HashMap<>();

        List<RoleEntity> listOfEntities = this.roleRepository.findAll();

        for(RoleEntity entity : listOfEntities)
        {
            mapOfRoles.put(entity.getValue(), entity);
        }

        this.mapOfRoles = mapOfRoles;
    }

    public RoleEntity getRoleEntity(AccountRegisterTypeEnum accountRegisterType)
    {
        return this.mapOfRoles.get(accountRegisterType.getValue());
    }

    public RoleEntity getRoleEntity(String roleValue)
    {
        return this.mapOfRoles.get(roleValue);
    }

    public Integer getRoleId(AccountRegisterTypeEnum accountRegisterType)
    {
        RoleEntity entity = this.mapOfRoles.get(accountRegisterType.getValue());
        if(entity == null)
        {
            return null;
        }

        return entity.getId();
    }

    public Integer getRoleId(String roleValue)
    {
        RoleEntity entity = this.mapOfRoles.get(roleValue);
        if(entity == null)
        {
            return null;
        }
        return entity.getId();
    }
}
