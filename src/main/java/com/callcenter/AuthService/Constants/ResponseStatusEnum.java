package com.callcenter.AuthService.Constants;

import org.springframework.http.HttpStatusCode;

public interface ResponseStatusEnum
{
    String getMessage();
    HttpStatusCode getStatusCode();
}
