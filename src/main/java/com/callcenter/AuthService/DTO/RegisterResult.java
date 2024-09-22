package com.callcenter.AuthService.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegisterResult
{
    protected boolean isSuccess;
    protected String message;

    public RegisterResult()
    {
        this.isSuccess = false;
        this.message = null;
    }

    public RegisterResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
        this.message = null;
    }

    public RegisterResult(boolean isSuccess, String message)
    {
        this.isSuccess = isSuccess;
        this.message = message;
    }

}
