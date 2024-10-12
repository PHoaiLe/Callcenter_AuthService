package com.callcenter.AuthService.DTO.Register;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class RegisterStrategyResult
{
    protected String recordId;

    public RegisterStrategyResult()
    {
        this.recordId = null;
    }

    public RegisterStrategyResult(String recordId)
    {
        this.recordId = recordId;
    }
}
