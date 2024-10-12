package com.callcenter.AuthService.DTO.Register.InternalOutput;

import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class EaPAccountRegisterStrategyResult extends RegisterStrategyResult {
    private String email;
    private String password;

    public EaPAccountRegisterStrategyResult(String recordId)
    {
        super(recordId);
        this.email = null;
        this.password = null;
    }

    public EaPAccountRegisterStrategyResult(String email, String password, String recordId)
    {
        super(recordId);
        this.email = email;
        this.password = password;
    }
}
