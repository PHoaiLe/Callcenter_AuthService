package com.callcenter.AuthService.Support.Builder.RefreshToken;


import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithoutData;
import com.callcenter.AuthService.DTO.RefreshToken.RefreshTokenResponseBody;
import com.callcenter.AuthService.Support.Builder.IApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.IApiResponseWithoutDataBuilder;

public class RefreshTokenExceptionResponseBuilder implements IApiResponseWithoutDataBuilder<TokenVerificationStatusEnum, RefreshTokenResponseBody>
{
    private ApiResponseWithoutData.ApiResponseWithoutDataBuilder builder;

    public RefreshTokenExceptionResponseBuilder()
    {
        this.builder = ApiResponseWithoutData.builder();
    }

    @Override
    public IApiResponseBuilder buildStatusCodeAndMessage(TokenVerificationStatusEnum statusEnum) {

        builder.message(statusEnum.getMessage());
        builder.statusCode(statusEnum.getStatusCode().value());
        return this;
    }

    @Override
    public IApiResponseBuilder builder() {

        RefreshTokenExceptionResponseBuilder newBuilder = new RefreshTokenExceptionResponseBuilder();
        newBuilder.builder = this.builder;

        return newBuilder;
    }

    @Override
    public IApiResponseBuilder reset() {
        this.builder = ApiResponseWithoutData.builder();

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

    @Deprecated
    @Override
    public IApiResponseBuilder buildData(RefreshTokenResponseBody data) {
        return this;
    }

    @Override
    public ApiResponse<RefreshTokenResponseBody> build() {
        return this.builder.build();
    }
}
