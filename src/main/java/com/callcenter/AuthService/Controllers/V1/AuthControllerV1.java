package com.callcenter.AuthService.Controllers.V1;

import com.callcenter.AuthService.DTO.External.EmailPasswordRegisterInfoDTO;
import com.callcenter.AuthService.Entities.EmailPasswordAuthenticationEntity;
import com.callcenter.AuthService.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EmailPasswordRegisterInfoDTO> registerAccount(@RequestBody EmailPasswordRegisterInfoDTO providedInfo)
    {
        EmailPasswordAuthenticationEntity createdEntity = accountService.create(providedInfo);

        EmailPasswordRegisterInfoDTO result = new EmailPasswordRegisterInfoDTO(createdEntity.getEmail(), createdEntity.getPassword());

        ResponseEntity a = new ResponseEntity<EmailPasswordRegisterInfoDTO>(result, HttpStatus.OK);

        return a;
    }

}
