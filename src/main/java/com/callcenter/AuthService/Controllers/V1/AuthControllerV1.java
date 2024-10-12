package com.callcenter.AuthService.Controllers.V1;

import com.callcenter.AuthService.Constants.AccountRegisterTypeEnum;
import com.callcenter.AuthService.Constants.Register.RegisterException;
import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.DTO.Login.ExternalInput.EaPSignInInfoRequest;
import com.callcenter.AuthService.DTO.Register.ExternalInput.EaPRegisterInfoRequest;
import com.callcenter.AuthService.DTO.Register.ExternalOutput.EaPRegisterInfoResponse;
import com.callcenter.AuthService.DTO.Register.InternalInput.EaPAccountRegisterInput;
import com.callcenter.AuthService.DTO.Register.RegisterResult;
import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.Entities.AccountEntity;
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
                EaPRegisterInfoResponse response = new EaPRegisterInfoResponse(HttpStatus.OK, "");

                //service task
                //initialize input
                EaPAccountRegisterInput registerInput = new EaPAccountRegisterInput();
                registerInput.setEmail(providedInfo.email());
                registerInput.setPassword(providedInfo.password());
                registerInput.setRoleValue(providedInfo.roleValue());
                registerInput.setRegisterType(AccountRegisterTypeEnum.EAP_AUTHENTICATION.getValue());

                try
                {
                    ServiceResult<AccountEntity> serviceResult = accountService.create(registerInput);

                    response.setStatusCode(RegisterStatusEnum.SUCCESS.getStatusCode());
                    response.setMessage(RegisterStatusEnum.SUCCESS.getMessage());
                }
                catch(RegisterException registerException)
                {
                    RegisterStatusEnum value = registerException.getValue();
                    response.setMessage(value.getMessage());
                    response.setStatusCode(value.getStatusCode());
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
                
                return null;
            }
        });

        return asyncResult;
    }

}
