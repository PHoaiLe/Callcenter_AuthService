package com.callcenter.AuthService.Support.Builder.SignInAPIResponse;

import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithData;
import com.callcenter.AuthService.DTO.SignIn.SignInResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithDataBuilder;

public class SignInApiResponseBuilder implements IApiResponseWithDataBuilder<SignInStatusEnum, SignInResponseBody>
{
    private ApiResponseWithData.ApiResponseWithDataBuilder<SignInResponseBody> apiResponseWithDataBuilder;


    public SignInApiResponseBuilder()
    {
        this.apiResponseWithDataBuilder = ApiResponseWithData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(SignInStatusEnum statusEnum) {
        this.apiResponseWithDataBuilder.statusCode(statusEnum.getStatusCode().value());
        this.apiResponseWithDataBuilder.message(statusEnum.getMessage());

        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        SignInApiResponseBuilder builder = new SignInApiResponseBuilder();
        builder.apiResponseWithDataBuilder = this.apiResponseWithDataBuilder;

        return builder;
    }

    @Override
    public IApiResponseBuilder reset() {
        return new SignInApiResponseBuilder();
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
    public IApiResponseBuilder buildData(SignInResponseBody data) {
        this.apiResponseWithDataBuilder.data(data);
        return this;
    }

    @Override
    public ApiResponse<SignInResponseBody> build() {
        return this.apiResponseWithDataBuilder.build();
    }
}
