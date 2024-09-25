package com.callcenter.AuthService.Constants;

import org.springframework.http.HttpStatusCode;

public record DefinedAPIStatus(
    HttpStatusCode statusCode,
    String message
) { }
