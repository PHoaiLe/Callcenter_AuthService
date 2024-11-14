package com.callcenter.AuthService.Support.Builder;

import com.callcenter.AuthService.DTO.ApiResponse;

public interface IApiResponseBuilder<STATUS_ENUM, RESPONSE_BODY>
{
    IApiResponseBuilder buildMessage(String message);
    IApiResponseBuilder buildStatusCode(int statusCode);
    IApiResponseBuilder buildStatusCodeAndMessage(STATUS_ENUM statusEnum);
    IApiResponseBuilder buildData(RESPONSE_BODY data);
    IApiResponseBuilder builder();
    IApiResponseBuilder reset();
    ApiResponse<RESPONSE_BODY> build();
}
