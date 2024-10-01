package com.callcenter.AuthService.Constants.Register;

import com.callcenter.AuthService.Constants.DefinedAPIStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class RegisterStatusConstants
{
    public static DefinedAPIStatus SUCCESS = new DefinedAPIStatus(HttpStatus.OK, "Registered account successfully!");
    public static DefinedAPIStatus EMAIL_ALREADY_USED = new DefinedAPIStatus(HttpStatus.CONFLICT, "The credential email has been used");
    public static DefinedAPIStatus UNRECOGNIZED_ROLE_VALUE = new DefinedAPIStatus(HttpStatus.BAD_REQUEST, "Invalid role value");
    public static DefinedAPIStatus UNRECOGNIZED_REGISTER_TYPE = new DefinedAPIStatus(HttpStatus.BAD_REQUEST, "Invalid value of the register type");
    public static DefinedAPIStatus VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT = new DefinedAPIStatus(HttpStatus.METHOD_NOT_ALLOWED, "Cannot create account since some error");

}