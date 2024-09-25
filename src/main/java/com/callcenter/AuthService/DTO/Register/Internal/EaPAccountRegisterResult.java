package com.callcenter.AuthService.DTO.Register.Internal;

import com.callcenter.AuthService.DTO.Register.RegisterResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class EaPAccountRegisterResult extends RegisterResult
{
    private String id;
    private String email;
    private String password;

    @Override
    public boolean isSuccess() {
        return super.isSuccess();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public int getStatusCode() {
        return super.getStatusCode();
    }

    @Override
    public void setStatusCode(int statusCode) {
        super.setStatusCode(statusCode);
    }

    public void setStatusCode(HttpStatusCode statusCode)
    {
        super.setStatusCode(statusCode.value());
    }
}
