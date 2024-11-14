package com.callcenter.AuthService.DTO.SignIn.ExternalOutput;

import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponseWithoutData;
import com.callcenter.AuthService.DTO.SignIn.SignInResponseBody;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Builder
@Getter
public class EaPSignInResponseBody extends SignInResponseBody
{
    private String accessToken;
    private Date accessTokenExpiration;
    private String refreshToken;
    private Date refreshTokenExpiration;

}
