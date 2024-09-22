package com.callcenter.AuthService.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterInputWrapper<T extends RegisterInput>
{
    private T object;

    public RegisterInputWrapper()
    {
        this.object = null;
    }

    public RegisterInputWrapper(T object)
    {
        this.object = object;
    }

}
