package com.callcenter.AuthService.DTO.Register;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Setter
@Getter
public class RegisterResult
{
    protected boolean isSuccess;
    protected int statusCode;
    protected String message;

    public RegisterResult()
    {
        this.isSuccess = false;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = null;
    }

    public RegisterResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = null;
    }

    public RegisterResult(boolean isSuccess, String message, int statusCode)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }
    public RegisterResult(boolean isSuccess, String message, HttpStatusCode statusCode)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode.value();
        this.message = message;
    }
}
