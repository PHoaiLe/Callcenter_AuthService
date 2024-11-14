package com.callcenter.AuthService.Constants.JWT;

import com.callcenter.AuthService.Constants.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum TokenVerificationStatusEnum implements ResponseStatusEnum
{
    SUCCESS(HttpStatus.OK, "Valid token!"),
    EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "The token is expired!"),
    UNSUPPORTED_JWT(HttpStatus.FORBIDDEN, "The token is invalid!"),
    MALFORMED_JWT(HttpStatus.FORBIDDEN, "Malformed token"),
    INVALID_SIGNATURE(HttpStatus.FORBIDDEN, "Invalid signature"),
    ILLEGAL_ARGUMENTS(HttpStatus.FORBIDDEN, "Illegal arguments of the token"),
    INVALID_AUTHORIZATION_INFO(HttpStatus.FORBIDDEN, "Invalid authorization info"),
    FORBIDDEN_PERMISSION_ACCESS(HttpStatus.FORBIDDEN, "Forbidden permission access");


    private HttpStatusCode statusCode;
    private String message;

    private TokenVerificationStatusEnum(HttpStatusCode statusCode, String message)
    {
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public HttpStatusCode getStatusCode()
    {
        return this.statusCode;
    }

    @Override
    public String getMessage()
    {
        return this.message;
    }

}
