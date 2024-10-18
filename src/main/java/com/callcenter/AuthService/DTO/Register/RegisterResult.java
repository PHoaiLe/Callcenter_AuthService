package com.callcenter.AuthService.DTO.Register;

import com.callcenter.AuthService.DTO.ServiceResult;
import com.callcenter.AuthService.Entities.AccountEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class RegisterResult
{
    AccountEntity accountEntity;

    public RegisterResult()
    {
        this.accountEntity = null;
    }

    public RegisterResult(AccountEntity accountEntity)
    {
        this.accountEntity = accountEntity;
    }
}
