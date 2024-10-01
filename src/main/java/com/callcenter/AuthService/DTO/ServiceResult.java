package com.callcenter.AuthService.DTO;

import com.callcenter.AuthService.Constants.DefinedAPIStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Setter
@Getter
public class ServiceResult
{
    protected boolean isSuccess;
    protected int statusCode;
    protected String message;

    public ServiceResult()
    {
        this.isSuccess = false;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = "";
    }

    public ServiceResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = "";
    }

    public ServiceResult(boolean isSuccess, int statusCode)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = "";
    }

    public ServiceResult(boolean isSuccess, int statusCode, String message)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ServiceResult(boolean isSuccess, HttpStatusCode statusCode, String message)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode.value();
        this.message = message;
    }

    public void setHttpResult(DefinedAPIStatus apiStatus)
    {
        this.statusCode = apiStatus.statusCode().value();
        this.message = apiStatus.message();
    }
}
