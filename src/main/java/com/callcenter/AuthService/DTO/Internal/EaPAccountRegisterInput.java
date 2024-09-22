package com.callcenter.AuthService.DTO.Internal;

import com.callcenter.AuthService.DTO.RegisterInput;
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
