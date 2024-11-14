package com.callcenter.AuthService.Support.Builder.SignInAPIResponse;

import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithoutData;
import com.callcenter.AuthService.DTO.SignIn.SignInResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithoutDataBuilder;

public class SignInApiResponseExceptionResponseBuilder implements IApiResponseWithoutDataBuilder<SignInStatusEnum, SignInResponseBody>
{
    private ApiResponseWithoutData.ApiResponseWithoutDataBuilder apiResponseWithoutDataBuilder;

    public SignInApiResponseExceptionResponseBuilder()
    {
        this.apiResponseWithoutDataBuilder = ApiResponseWithoutData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(SignInStatusEnum statusEnum) {
        this.apiResponseWithoutDataBuilder.statusCode(statusEnum.getStatusCode().value());
        this.apiResponseWithoutDataBuilder.message(statusEnum.getMessage());
        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        SignInApiResponseExceptionResponseBuilder builder = new SignInApiResponseExceptionResponseBuilder();
        builder.apiResponseWithoutDataBuilder = apiResponseWithoutDataBuilder;

        return builder;
    }

    @Override
    public IApiResponseBuilder reset() {
        return new SignInApiResponseExceptionResponseBuilder();
    }

    @Override
    public IApiResponseBuilder buildMessage(String message) {
        this.apiResponseWithoutDataBuilder.message(message);
        return this;
    }

    @Override
    public IApiResponseBuilder buildStatusCode(int statusCode) {
        this.apiResponseWithoutDataBuilder.statusCode(statusCode);
        return this;
    }

    @Override
    @Deprecated
    public IApiResponseBuilder buildData(SignInResponseBody data) {
        return this;
    }

    @Override
    public ApiResponse<SignInResponseBody> build() {
        return this.apiResponseWithoutDataBuilder.build();
    }
}
