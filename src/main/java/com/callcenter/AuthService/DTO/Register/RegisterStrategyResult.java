package com.callcenter.AuthService.DTO.Register;

import com.callcenter.AuthService.Constants.DefinedAPIStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@Setter
@Getter
public class RegisterStrategyResult
{
    protected boolean isSuccess;
    protected int statusCode;

    protected String recordId;
    protected String message;

    public RegisterStrategyResult()
    {
        this.isSuccess = false;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = "";
        this.recordId = null;
    }

    public RegisterStrategyResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
        this.statusCode = HttpStatus.NOT_IMPLEMENTED.value();
        this.message = "";
        this.recordId = null;
    }

    public RegisterStrategyResult(boolean isSuccess, int statusCode)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = "";
        this.recordId = null;
    }

    public RegisterStrategyResult(boolean isSuccess, int statusCode, String message)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
        this.recordId = null;
    }

    public RegisterStrategyResult(boolean isSuccess, int statusCode, String message, String recordId)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
        this.recordId = recordId;
    }

    public RegisterStrategyResult(boolean isSuccess, HttpStatusCode statusCode, String message)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode.value();
        this.message = message;
        this.recordId = null;
    }

    public RegisterStrategyResult(boolean isSuccess, HttpStatusCode statusCode, String message, String recordId)
    {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode.value();
        this.message = message;
        this.recordId = recordId;
    }

    public void setHttpResult(DefinedAPIStatus apiStatus)
    {
        this.statusCode = apiStatus.statusCode().value();
        this.message = apiStatus.message();
    }
}
