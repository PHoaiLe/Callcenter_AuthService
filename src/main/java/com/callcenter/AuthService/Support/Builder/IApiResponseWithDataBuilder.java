package com.callcenter.AuthService.Support.Builder;

import com.callcenter.AuthService.DTO.ApiResponse;

public interface IApiResponseWithDataBuilder<STATUS_ENUM, RESPONSE_BODY> extends IApiResponseBuilder<STATUS_ENUM, RESPONSE_BODY>
{
    @Override
    IApiResponseBuilder buildMessage(String message);

    @Override
    IApiResponseBuilder buildStatusCode(int statusCode);

    @Override
    IApiResponseBuilder buildData(RESPONSE_BODY data);

    @Override
    ApiResponse<RESPONSE_BODY> build();
}

