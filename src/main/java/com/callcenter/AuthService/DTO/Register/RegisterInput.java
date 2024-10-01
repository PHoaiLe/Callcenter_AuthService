package com.callcenter.AuthService.DTO.Register;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Constants.AccountRoleEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterInput
{
    protected String roleValue;
    protected String registerType;

    public RegisterInput()
    {
        this.roleValue = null;
        this.registerType = null;
    }

    //getter
    public String getRoleValue()
    {
        return this.roleValue;
    }

    public String getRegisterType()
    {
        return this.registerType;
    }


    //setter
    public void setRoleValue(String roleValue)
    {
        this.roleValue = roleValue;
    }

    public void setRoleValue(AccountRoleEnum accountRoleEnum)
    {
        this.roleValue = accountRoleEnum.getValue();
    }

    public void setRegisterType(AccountRegisterTypeEnum registerType)
    {
        this.registerType = registerType.getValue();
    }

    public void setRegisterType(String registerType)
    {
        this.registerType = registerType;
    }
}
