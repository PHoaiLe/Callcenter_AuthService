package com.callcenter.AuthService.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterResultWrapper<T extends RegisterResult>
{
    private T object;

    public RegisterResultWrapper()
    {
        this.object = null;
    }

    public RegisterResultWrapper(T object)
    {
        this.object = object;
    }

}
