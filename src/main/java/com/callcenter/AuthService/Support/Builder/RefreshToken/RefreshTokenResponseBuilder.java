package com.callcenter.AuthService.Support.Builder.RefreshToken;

import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithData;
import com.callcenter.AuthService.DTO.RefreshToken.RefreshTokenResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithDataBuilder;

public class RefreshTokenResponseBuilder implements IApiResponseWithDataBuilder<TokenVerificationStatusEnum, RefreshTokenResponseBody>
{
    private ApiResponseWithData.ApiResponseWithDataBuilder<RefreshTokenResponseBody> builder;

    public RefreshTokenResponseBuilder()
    {
        this.builder = ApiResponseWithData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(TokenVerificationStatusEnum statusEnum) {

        this.builder.statusCode(statusEnum.getStatusCode().value());
        this.builder.message(statusEnum.getMessage());
        return this;
    }

    @Override
    public IApiResponseBuilder builder() {
        RefreshTokenResponseBuilder newBuilder = new RefreshTokenResponseBuilder();
        newBuilder.builder = this.builder;

        return newBuilder;
    }

    @Override
    public IApiResponseBuilder reset() {
        this.builder = ApiResponseWithData.builder();
        return this;
    }

    @Override
    public IApiResponseBuilder buildMessage(String message) {
        this.builder.message(message);

        return this;
    }

    @Override
    public IApiResponseBuilder buildStatusCode(int statusCode) {
        this.builder.statusCode(statusCode);
        return this;
    }

    @Override
    public IApiResponseBuilder buildData(RefreshTokenResponseBody data) {
        this.builder.data(data);

        return this;
    }

    @Override
    public ApiResponse<RefreshTokenResponseBody> build() {
        return this.builder.build();
    }
}
