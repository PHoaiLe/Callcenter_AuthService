package com.callcenter.AuthService.Controllers.V1;

import com.callcenter.AuthService.DTO.ExternalInput.EaPRegisterInfoRequest;
import com.callcenter.AuthService.DTO.Internal.EaPAccountRegisterInput;
import com.callcenter.AuthService.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    protected AccountService accountService;

    @GetMapping(path = "/welcome")
    public ResponseEntity<String> welcome()
    {
        String welcomeString = "Welcome to Auth Service!!!";
        ResponseEntity<String> result = new ResponseEntity<>(welcomeString, HttpStatus.OK);
        return result;
    }

    @PostMapping(value = "/register/standard")
    public CompletableFuture<ResponseEntity<?>> registerAccount(@RequestBody EaPRegisterInfoRequest providedInfo)
    {
        System.out.println(Thread.currentThread().getName());
        CompletableFuture<ResponseEntity<?>> asyncResult = CompletableFuture.supplyAsync(new Supplier<ResponseEntity<?>>() {
            @Override
            public ResponseEntity<?> get() {
                EaPAccountRegisterInput input = new EaPAccountRegisterInput();
                input.setEmail(providedInfo.email());
                input.setPassword(providedInfo.password());

                accountService.create(input);

                return null;
            }
        });

        return asyncResult;
    }

}
