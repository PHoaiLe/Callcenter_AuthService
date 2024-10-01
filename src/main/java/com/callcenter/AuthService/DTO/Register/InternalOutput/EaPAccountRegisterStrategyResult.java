package com.callcenter.AuthService.DTO.Register.InternalOutput;

import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class EaPAccountRegisterStrategyResult extends RegisterStrategyResult
{
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
