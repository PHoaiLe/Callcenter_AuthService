package com.callcenter.AuthService.Controllers.V1;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.Constants.SignIn.SignInException;
import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.SignIn.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.ExternalInput.EaPRegisterInfoRequest;
import com.callcenter.AuthService.DTO.Register.ExternalOutput.EaPRegisterInfoResponse;
import com.callcenter.AuthService.DTO.Register.InternalInput.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.DTO.SignIn.ExternalOutput.EaPSignInResponse;
import com.callcenter.AuthService.DTO.SignIn.SignInResult;
import com.callcenter.AuthService.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                EaPRegisterInfoResponse response = new EaPRegisterInfoResponse(RegisterStatusEnum.SUCCESS);

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

                    response.setStatusAndMessage(RegisterStatusEnum.SUCCESS);
                }
                catch(RegisterException registerException)
                {
                    response.setStatusAndMessage(registerException.getValue());
                }


                ResponseEntity apiResponse = new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));

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
                EaPSignInResponse response = new EaPSignInResponse(SignInStatusEnum.SUCCESS);

                try
                {
                    ServiceResult<SignInResult> serviceResult = accountService.signInByEmailPassword(provideInfo);
                    SignInResult signInResult = serviceResult.getObject();

                    response.setAccessToken(signInResult.getAccessToken());
                    response.setAccessTokenExpiration(signInResult.getAccessTokenExpiration());
                    response.setRefreshToken(signInResult.getRefreshToken());
                    response.setRefreshTokenExpiration(signInResult.getRefreshTokenExpiration());
                    response.setStatusAndMessage(SignInStatusEnum.SUCCESS);
                }
                catch (SignInException signInException)
                {
                    response.setStatusAndMessage(signInException.getValue());
                }

                ResponseEntity apiResponse = new ResponseEntity(response, HttpStatusCode.valueOf(response.getStatusCode()));

                return apiResponse;
            }
        });

        return asyncResult;
    }

}
