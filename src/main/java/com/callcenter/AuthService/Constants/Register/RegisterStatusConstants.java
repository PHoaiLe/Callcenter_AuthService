package com.callcenter.AuthService.Constants.Register;

import com.callcenter.AuthService.Constants.DefinedAPIStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class RegisterStatusConstants
{
    public static DefinedAPIStatus SUCCESS = new DefinedAPIStatus(HttpStatus.OK, "Registered account successfully!");
    public static  DefinedAPIStatus EMAIL_ALREADY_USED = new DefinedAPIStatus(HttpStatus.CONFLICT, "The credential email has been used");
}