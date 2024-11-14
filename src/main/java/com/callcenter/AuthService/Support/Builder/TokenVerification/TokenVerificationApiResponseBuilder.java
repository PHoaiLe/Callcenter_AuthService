package com.callcenter.AuthService.Support.Builder.TokenVerification;

import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithData;
import com.callcenter.AuthService.DTO.TokenVerification.ExternalOutput.TokenVerificationResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithDataBuilder;

public class TokenVerificationApiResponseBuilder implements IApiResponseWithDataBuilder<TokenVerificationStatusEnum, TokenVerificationResponseBody>
{
    private ApiResponseWithData.ApiResponseWithDataBuilder<TokenVerificationResponseBody> apiResponseWithDataBuilder;

    public TokenVerificationApiResponseBuilder()
    {
        this.apiResponseWithDataBuilder = ApiResponseWithData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(TokenVerificationStatusEnum statusEnum) {
        this.apiResponseWithDataBuilder.statusCode(statusEnum.getStatusCode().value());
        this.apiResponseWithDataBuilder.message(statusEnum.getMessage());

        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        TokenVerificationApiResponseBuilder builder = new TokenVerificationApiResponseBuilder();
        builder.apiResponseWithDataBuilder = this.apiResponseWithDataBuilder;

        return builder;
    }

    @Override
    public IApiResponseBuilder reset() {
        return new TokenVerificationApiResponseBuilder();
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
    public IApiResponseBuilder buildData(TokenVerificationResponseBody data) {
        this.apiResponseWithDataBuilder.data(data);
        return this;
    }

    @Override
    public ApiResponse<TokenVerificationResponseBody> build() {
        return this.apiResponseWithDataBuilder.build();
    }
}
