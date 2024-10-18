package com.callcenter.AuthService.Constants.Register;

import com.callcenter.AuthService.Constants.ResponseStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum RegisterStatusEnum implements ResponseStatusEnum
{
    SUCCESS(HttpStatus.OK, "Registered account successfully!"),
    EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "The credential email has been used"),
    UNRECOGNIZED_ROLE_VALUE(HttpStatus.BAD_REQUEST, "Invalid role value"),
    UNRECOGNIZED_REGISTER_TYPE(HttpStatus.BAD_REQUEST, "Invalid value of the register type"),
    VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT(HttpStatus.METHOD_NOT_ALLOWED, "Cannot create account since some error"),
    UNSUPPORTED_REGISTER_STRATEGY(HttpStatus.NOT_IMPLEMENTED, "Unsupported register strategy");


    private HttpStatusCode statusCode;
    private String message;

    private RegisterStatusEnum(HttpStatusCode statusCode, String message)
    {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }


}
