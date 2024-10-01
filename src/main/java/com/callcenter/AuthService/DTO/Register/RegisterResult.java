package com.callcenter.AuthService.DTO.Register;

import com.callcenter.AuthService.DTO.ServiceResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class RegisterResult extends ServiceResult
{
    public RegisterResult()
    {
        super();
    }

    public RegisterResult(boolean isSuccess)
    {
        super(isSuccess);
    }

    public RegisterResult(boolean isSuccess, int statusCode)
    {
        super(isSuccess, statusCode);
    }

    public RegisterResult(boolean isSuccess, int statusCode, String message)
    {
        super(isSuccess, statusCode, message);
    }

    public RegisterResult(boolean isSuccess, HttpStatusCode statusCode, String message)
    {
        super(isSuccess, statusCode, message);
    }
}
