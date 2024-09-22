package com.callcenter.AuthService.DTO.Internal;

import com.callcenter.AuthService.DTO.RegisterResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EaPAccountRegisterResult extends RegisterResult
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
}
