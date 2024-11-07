package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Entities.RoleEntity;
import com.callcenter.AuthService.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRoleService
{
    private RoleRepository roleRepository;

    public AccountRoleService() {}

    @Autowired
    public AccountRoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    public RoleEntity getRoleEntity(AccountRegisterTypeEnum accountRegisterType)
    {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByValue(accountRegisterType.getValue());
        if(optionalRoleEntity.isEmpty())
        {
            return null;
        }

        return optionalRoleEntity.get();
    }

    public RoleEntity getRoleEntity(String roleValue)
    {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByValue(roleValue);
        if(optionalRoleEntity.isEmpty())
        {
            return null;
        }

        return optionalRoleEntity.get();
    }

    public Integer getRoleId(AccountRegisterTypeEnum accountRegisterType)
    {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByValue(accountRegisterType.getValue());
        if(optionalRoleEntity.isEmpty())
        {
            return null;
        }

        return optionalRoleEntity.get().getId();
    }

    public Integer getRoleId(String roleValue)
    {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByValue(roleValue);
        if(optionalRoleEntity.isEmpty())
        {
            return null;
        }

        return optionalRoleEntity.get().getId();
    }

    public RoleEntity getRoleEntityById(Integer roleId)
    {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(roleId);
        if(optionalRoleEntity.isEmpty())
        {
            return null;
        }

        return optionalRoleEntity.get();
    }

}
