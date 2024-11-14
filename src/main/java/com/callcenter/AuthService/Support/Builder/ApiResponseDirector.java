package com.callcenter.AuthService.Support.Builder;

import com.callcenter.AuthService.Constants.ResponseStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import lombok.Builder;

@Builder
public class ApiResponseDirector<STATUS_ENUM, RESPONSE_BODY>
{
    private IApiResponseBuilder<STATUS_ENUM, RESPONSE_BODY> apiResponseBuilder;

    private ApiResponseDirector(IApiResponseBuilder apiResponseBuilder)
    {
        this.apiResponseBuilder = apiResponseBuilder;
    }

    public ApiResponse buildApiResponse(STATUS_ENUM statusEnum)
    {
        return this.apiResponseBuilder.buildStatusCodeAndMessage(statusEnum).build();
    }

    public ApiResponse buildApiResponse(STATUS_ENUM statusEnum, RESPONSE_BODY data)
    {
        return this.apiResponseBuilder.buildStatusCodeAndMessage(statusEnum)
                .buildData(data)
                .build();
    }
}
