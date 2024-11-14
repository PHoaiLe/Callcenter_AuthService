package com.callcenter.AuthService.Support.Builder.RegisterAPIResponse;

import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithData;
import com.callcenter.AuthService.DTO.Register.ExternalOutput.RegisterResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithDataBuilder;

public class RegisterApiResponseBuilder implements IApiResponseWithDataBuilder<RegisterStatusEnum, RegisterResponseBody>
{
    private ApiResponseWithData.ApiResponseWithDataBuilder<RegisterResponseBody> apiResponseWithDataBuilder;

    public RegisterApiResponseBuilder()
    {
        this.apiResponseWithDataBuilder = ApiResponseWithData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(RegisterStatusEnum registerStatusEnum) {
        this.apiResponseWithDataBuilder.statusCode(registerStatusEnum.getStatusCode().value());
        this.apiResponseWithDataBuilder.message(registerStatusEnum.getMessage());
        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        RegisterApiResponseBuilder builder = new RegisterApiResponseBuilder();
        builder.apiResponseWithDataBuilder = this.apiResponseWithDataBuilder;

        return builder;
    }

    @Override
    public IApiResponseBuilder buildMessage(String message) {
        this.apiResponseWithDataBuilder.message(message);
        return this;
    }

    @Override
    public IApiResponseBuilder buildStatusCode(int statusCode) {
        this.apiResponseWithDataBuilder.statusCode(statusCode);
        return this;
    }

    @Override
    public IApiResponseBuilder buildData(RegisterResponseBody data) {
        this.apiResponseWithDataBuilder.data(data);
        return this;
    }

    @Override
    public ApiResponse<RegisterResponseBody> build() {
        return this.apiResponseWithDataBuilder.build();
    }

    @Override
    public IApiResponseBuilder reset() {
        return new RegisterApiResponseBuilder();
    }
}
