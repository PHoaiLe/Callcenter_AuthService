package com.callcenter.AuthService.Constants.SignIn;


public class SignInException extends Exception
{
    public final static SignInException INVALID_EMAIL_PASSWORD = new SignInException(SignInStatusEnum.INVALID_EMAIL_PASSWORD);
    public final static SignInException ACCOUNT_NOT_FOUND = new SignInException(SignInStatusEnum.ACCOUNT_NOT_FOUND);

    private SignInStatusEnum value;
    private SignInException()
    {
        this.value = null;
    }

    private SignInException(SignInStatusEnum value)
    {
        this.value = value;
    }

    public SignInStatusEnum getValue() {
        return value;
    }
}
