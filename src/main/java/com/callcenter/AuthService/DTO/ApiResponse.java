package com.callcenter.AuthService.DTO;

import org.springframework.http.HttpStatusCode;

public abstract class ApiResponse<RESPONSE_BODY>
{
    public abstract String getMessage();
    public abstract int getIntStatusCode();
    public abstract HttpStatusCode getStatusCode();
    public abstract RESPONSE_BODY getData();
}