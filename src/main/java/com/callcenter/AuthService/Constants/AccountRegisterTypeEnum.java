package com.callcenter.AuthService.Constants;

public enum AccountRegisterTypeEnum
{
    EAP_AUTHENTICATION("EAP_AUTHENTICATION"),
    GOOGLE_AUTHENTICATION("GOOGLE_AUTHENTICATION"),
    FACEBOOK_AUTHENTICATION("FACEBOOK_AUTHENTICATION");

    private String value;

    private AccountRegisterTypeEnum(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return this.value;
    }

}
