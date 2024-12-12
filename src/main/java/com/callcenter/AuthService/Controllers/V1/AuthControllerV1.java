package com.callcenter.AuthService.Controllers.V1;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Constants.JWT.TokenVerificationException;
import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.Constants.SignIn.SignInException;
import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.ApiResponse;
import com.callcenter.AuthService.DTO.ApiResponseWithData;
import com.callcenter.AuthService.DTO.Endpoint.APIEndPoint;
import com.callcenter.AuthService.DTO.Endpoint.APIMethods;
import com.callcenter.AuthService.DTO.RefreshToken.RefreshTokenRequest;
import com.callcenter.AuthService.DTO.RefreshToken.RefreshTokenResponseBody;
import com.callcenter.AuthService.DTO.RefreshToken.RefreshTokenResult;
import com.callcenter.AuthService.DTO.Register.ExternalOutput.RegisterResponseBody;
import com.callcenter.AuthService.DTO.SignIn.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.ExternalInput.EaPRegisterInfoRequest;
import com.callcenter.AuthService.DTO.Register.InternalInput.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.DTO.SignIn.ExternalOutput.EaPSignInResponseBody;
import com.callcenter.AuthService.DTO.SignIn.SignInResponseBody;
import com.callcenter.AuthService.DTO.SignIn.SignInResult;
import com.callcenter.AuthService.DTO.TokenVerification.ExternalInput.TokenVerificationRequest;
import com.callcenter.AuthService.DTO.TokenVerification.ExternalOutput.TokenVerificationResponseBody;
import com.callcenter.AuthService.DTO.TokenVerification.TokenVerificationResult;
import com.callcenter.AuthService.Services.AccountService;
import com.callcenter.AuthService.Support.Builder.ApiResponseDirector;
import com.callcenter.AuthService.Support.Builder.RefreshToken.RefreshTokenExceptionResponseBuilder;
import com.callcenter.AuthService.Support.Builder.RefreshToken.RefreshTokenResponseBuilder;
import com.callcenter.AuthService.Support.Builder.RegisterAPIResponse.RegisterApiExceptionResponseBuilder;
import com.callcenter.AuthService.Support.Builder.RegisterAPIResponse.RegisterApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.SignInAPIResponse.SignInApiResponseBuilder;
import com.callcenter.AuthService.Support.Builder.SignInAPIResponse.SignInApiResponseExceptionResponseBuilder;
import com.callcenter.AuthService.Support.Builder.TokenVerification.TokenVerificationApiExceptionResponseBuilder;
import com.callcenter.AuthService.Support.Builder.TokenVerification.TokenVerificationApiResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Controller
@RestController
@RequestMapping(path = "apis/v1/auth")
public class AuthControllerV1
{
    protected AccountService accountService;

    @Autowired
    public AuthControllerV1(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping(path = "/welcome")
    public ResponseEntity<String> welcome()
    {
        String welcomeString = "Welcome to Auth Service!!!";
        ResponseEntity<String> result = new ResponseEntity<>(welcomeString, HttpStatus.OK);
        return result;
    }

    @PostMapping(value = "/register/standard")
    public CompletableFuture<ResponseEntity> registerAccount(@RequestBody EaPRegisterInfoRequest providedInfo)
    {
        CompletableFuture<ResponseEntity> asyncResult = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {

                //initialize api response
                ApiResponse<RegisterResponseBody> response = null;
                ApiResponseDirector.ApiResponseDirectorBuilder<RegisterStatusEnum, RegisterResponseBody> apiResponseDirectorBuilder = ApiResponseDirector.builder();

                //service task
                //initialize input
                EaPAccountRegisterInput registerInput = new EaPAccountRegisterInput();
                registerInput.setEmail(providedInfo.email());
                registerInput.setPassword(providedInfo.password());
                registerInput.setRoleValue(providedInfo.roleValue());
                registerInput.setRegisterType(AccountRegisterTypeEnum.EAP_AUTHENTICATION.getValue());

                try
                {
                    ServiceResult<RegisterResult> serviceResult = accountService.create(registerInput);

                    apiResponseDirectorBuilder.apiResponseBuilder(new RegisterApiResponseBuilder());
                    response = apiResponseDirectorBuilder.build().buildApiResponse(RegisterStatusEnum.SUCCESS, new RegisterResponseBody());
                }
                catch(RegisterException registerException)
                {
                    apiResponseDirectorBuilder.apiResponseBuilder(new RegisterApiExceptionResponseBuilder());
                    response = apiResponseDirectorBuilder.build().buildApiResponse(registerException.getValue());
                }


                ResponseEntity apiResponse = new ResponseEntity<>(response, response.getStatusCode());

                return apiResponse;
            }
        });

        return asyncResult;
    }

    @PostMapping(value = "/login/standard")
    public CompletableFuture<ResponseEntity> loginByEaPAuthentication(@RequestBody EaPSignInInfoRequest provideInfo)
    {
        CompletableFuture<ResponseEntity> asyncResult = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get()
            {
                ApiResponse<EaPSignInResponseBody> response = null;
                ApiResponseDirector.ApiResponseDirectorBuilder<SignInStatusEnum, SignInResponseBody> apiResponseDirectorBuilder = ApiResponseDirector.builder();

                try
                {
                    ServiceResult<SignInResult> serviceResult = accountService.signInByEmailPassword(provideInfo);
                    SignInResult signInResult = serviceResult.getObject();

                    EaPSignInResponseBody responseBody = EaPSignInResponseBody.builder()
                            .accessToken(signInResult.getAccessToken())
                            .accessTokenExpiration(signInResult.getAccessTokenExpiration())
                            .refreshToken(signInResult.getRefreshToken())
                            .refreshTokenExpiration(signInResult.getRefreshTokenExpiration())
                            .build();

                    apiResponseDirectorBuilder.apiResponseBuilder(new SignInApiResponseBuilder());

                    response = apiResponseDirectorBuilder.build().buildApiResponse(SignInStatusEnum.SUCCESS, responseBody);
                }
                catch (SignInException signInException)
                {
                    apiResponseDirectorBuilder.apiResponseBuilder(new SignInApiResponseExceptionResponseBuilder());
                    response = apiResponseDirectorBuilder.build().buildApiResponse(signInException.getValue());
                }

                ResponseEntity apiResponse = new ResponseEntity(response, response.getStatusCode());

                return apiResponse;
            }
        });

        return asyncResult;
    }

    @PostMapping("/verify/access")
    public CompletableFuture<ResponseEntity> verifyAccessToken(@RequestBody(required = true)TokenVerificationRequest requestBody)
    {
        CompletableFuture<ResponseEntity> asyncResult = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {
                Date currentTime = new Date();

                ApiResponse<TokenVerificationResponseBody> response = null;
                ApiResponseDirector.ApiResponseDirectorBuilder<TokenVerificationStatusEnum, TokenVerificationResponseBody> apiResponseDirectorBuilder = ApiResponseDirector.builder();

                APIEndPoint endPoint = APIEndPoint.builder()
                        .httpMethod(APIMethods.toMethod(requestBody.targetAPIMethod()))
                        .path(requestBody.targetAPIPath())
                        .build();

                try
                {
                    ServiceResult<TokenVerificationResult> serviceResult = accountService.verifyAccessToken(requestBody.token(), currentTime, endPoint);
                    TokenVerificationResult verificationResult = serviceResult.getObject();

                    TokenVerificationResponseBody responseBody = TokenVerificationResponseBody
                            .builder()
                            .key(verificationResult.getTargetEntity().getId())
                            .role(verificationResult.getTargetEntity().getRole())
                            .targetPermission(verificationResult.getTargetPermission())
                            .build();

                    apiResponseDirectorBuilder.apiResponseBuilder(new TokenVerificationApiResponseBuilder());

                    response = apiResponseDirectorBuilder.build().buildApiResponse(TokenVerificationStatusEnum.SUCCESS, responseBody);
                }
                catch (TokenVerificationException exception)
                {
                    apiResponseDirectorBuilder.apiResponseBuilder(new TokenVerificationApiExceptionResponseBuilder());

                    response = apiResponseDirectorBuilder.build().buildApiResponse(exception.getValue());
                }

                return new ResponseEntity(response, response.getStatusCode());
            }
        });

        return asyncResult;
    }

    @PostMapping("/refresh")
    public CompletableFuture<ResponseEntity> refreshToken(@RequestBody RefreshTokenRequest requestBody)
    {
        CompletableFuture<ResponseEntity> asyncResult = CompletableFuture.supplyAsync(new Supplier<ResponseEntity>() {
            @Override
            public ResponseEntity get() {

                Date timeReceivedRequest = new Date();
                String token = requestBody.token();

                ApiResponse<RefreshTokenResult> apiResponse = null;

                ApiResponseDirector.ApiResponseDirectorBuilder<TokenVerificationStatusEnum, RefreshTokenResponseBody> apiResponseDirectorBuilder = ApiResponseDirector.builder();

                try
                {
                    ServiceResult<RefreshTokenResult> serviceResult = accountService.refreshToken(token, timeReceivedRequest);

                    RefreshTokenResult refreshTokenResult = serviceResult.getObject();

                    RefreshTokenResponseBody responseBody = RefreshTokenResponseBody.builder()
                                            .accessToken(refreshTokenResult.getAccessToken())
                                            .accessTokenExpiration(refreshTokenResult.getAccessTokenExpiration())
                                            .refreshToken(refreshTokenResult.getRefreshToken())
                                            .refreshTokenExpiration(refreshTokenResult.getRefreshTokenExpiration())
                                                    .build();

                    apiResponseDirectorBuilder.apiResponseBuilder(new RefreshTokenResponseBuilder());
                    apiResponse = apiResponseDirectorBuilder.build().buildApiResponse(TokenVerificationStatusEnum.SUCCESS, responseBody);
                }
                catch(TokenVerificationException exception)
                {
                    apiResponseDirectorBuilder.apiResponseBuilder(new RefreshTokenExceptionResponseBuilder());
                    apiResponse = apiResponseDirectorBuilder.build().buildApiResponse(exception.getValue());
                }

                return new ResponseEntity(apiResponse, apiResponse.getStatusCode());
            }
        });

        return asyncResult;
    }


}
