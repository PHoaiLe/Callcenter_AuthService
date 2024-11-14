package com.callcenter.AuthService.Support.Builder.RegisterAPIResponse;

import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithoutData;
import com.callcenter.AuthService.DTO.Register.ExternalOutput.RegisterResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithoutDataBuilder;

public class RegisterApiExceptionResponseBuilder implements IApiResponseWithoutDataBuilder<RegisterStatusEnum, RegisterResponseBody>
{
    private ApiResponseWithoutData.ApiResponseWithoutDataBuilder responseWithoutDataBuilder;

    public RegisterApiExceptionResponseBuilder()
    {
        this.responseWithoutDataBuilder = ApiResponseWithoutData.builder();
    }

    @Override
    public IApiResponseBuilder buildMessage(String message) {
        this.responseWithoutDataBuilder.message(message);
        return this;
    }

    @Override
    public IApiResponseBuilder buildStatusCode(int statusCode) {
        this.responseWithoutDataBuilder.statusCode(statusCode);
        return this;
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(RegisterStatusEnum registerStatusEnum) {
        this.responseWithoutDataBuilder.statusCode(registerStatusEnum.getStatusCode().value());
        this.responseWithoutDataBuilder.message(registerStatusEnum.getMessage());
        return this;
    }

    @Override
    @Deprecated
    public IApiResponseBuilder buildData(RegisterResponseBody data) {
        return this;
    }

    @Override
    public ApiResponse<RegisterResponseBody> build() {
        return this.responseWithoutDataBuilder.build();
    }

    @Override
    public IApiResponseBuilder builder() {
        RegisterApiExceptionResponseBuilder builder = new RegisterApiExceptionResponseBuilder();
        builder.responseWithoutDataBuilder = this.responseWithoutDataBuilder;
        return builder;
    }

    @Override
    public IApiResponseBuilder reset() {
        return new RegisterApiExceptionResponseBuilder();
    }
}
