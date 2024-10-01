package com.callcenter.AuthService.Constants;

public enum AccountStatusEnum
{
    INITIALIZED("INITIALIZED"),
    BLOCKED("BLOCKED"),
    AVAILABLE("AVAILABLE");


    private String value;

    private AccountStatusEnum(String valueOfStatus)
    {
        this.value = valueOfStatus;
    }

    public String getValue()
    {
        return this.value;
    }
}
