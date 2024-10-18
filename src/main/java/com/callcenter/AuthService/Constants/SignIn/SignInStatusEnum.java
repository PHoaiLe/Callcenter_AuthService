package com.callcenter.AuthService.Constants.SignIn;


import com.callcenter.AuthService.Constants.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum SignInStatusEnum implements ResponseStatusEnum
{
    SUCCESS(HttpStatus.OK, "Sign in successfully!"),
    INVALID_EMAIL_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid credentials"),
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "Account not found");

    private HttpStatusCode statusCode;
    private String message;


    private SignInStatusEnum(HttpStatusCode statusCode, String message)
    {
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatusCode getStatusCode()
    {
        return this.statusCode;
    }

    public String getMessage()
    {
        return this.message;
    }
}
