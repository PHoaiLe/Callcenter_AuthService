package com.callcenter.AuthService.Constants;

public enum AccountRoleEnum
{

    ADMIN("ADMIN_NO1"),
    CUSTOMER("CUSTOMER"),
    DRIVER("DRIVER");

    private String value;
    private AccountRoleEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }

}
