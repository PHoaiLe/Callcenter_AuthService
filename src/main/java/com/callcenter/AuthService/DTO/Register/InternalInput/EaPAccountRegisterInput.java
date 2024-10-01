package com.callcenter.AuthService.DTO.Register.InternalInput;

import com.callcenter.AuthService.DTO.Register.RegisterInput;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EaPAccountRegisterInput extends RegisterInput
{
    private String email;
    private String password;


}
