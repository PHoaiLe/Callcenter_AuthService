package com.callcenter.AuthService.Support.Builder.TokenVerification;

import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithoutData;
import com.callcenter.AuthService.DTO.TokenVerification.ExternalOutput.TokenVerificationResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithoutDataBuilder;

public class TokenVerificationApiExceptionResponseBuilder implements IApiResponseWithoutDataBuilder<TokenVerificationStatusEnum, TokenVerificationResponseBody>
{
    private ApiResponseWithoutData.ApiResponseWithoutDataBuilder apiResponseWithoutDataBuilder;

    public TokenVerificationApiExceptionResponseBuilder()
    {
        this.apiResponseWithoutDataBuilder = ApiResponseWithoutData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(TokenVerificationStatusEnum statusEnum) {
        this.apiResponseWithoutDataBuilder.statusCode(statusEnum.getStatusCode().value());
        this.apiResponseWithoutDataBuilder.message(statusEnum.getMessage());

        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        TokenVerificationApiExceptionResponseBuilder builder = new TokenVerificationApiExceptionResponseBuilder();
        builder.apiResponseWithoutDataBuilder = this.apiResponseWithoutDataBuilder;

        return builder;
    }

    @Override
    public IApiResponseBuilder reset() {
        return new TokenVerificationApiExceptionResponseBuilder();
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
    public IApiResponseBuilder buildData(TokenVerificationResponseBody data) {
        return this;
    }

    @Override
    public ApiResponse<TokenVerificationResponseBody> build() {
        return this.apiResponseWithoutDataBuilder.build();
    }
}
