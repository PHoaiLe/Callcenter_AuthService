package com.callcenter.AuthService.Services;

import com.callcenter.AuthService.DTO.RegisterInput;
import com.callcenter.AuthService.DTO.RegisterResult;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProvider;
import com.callcenter.AuthService.Services.RegisterService.RegisterServiceProviderV2;
import com.callcenter.AuthService.Services.RegisterService.RegisterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
public class AccountService
{
//    private RegisterServiceProvider registerServiceProvider = RegisterServiceProvider.getInstance();

    private RegisterServiceProviderV2 registerServiceProviderV2;


    public AccountService()
    {}

    @Autowired
    public AccountService(RegisterServiceProviderV2 registerServiceProviderV2)
    {
        this.registerServiceProviderV2 = registerServiceProviderV2;
    }

    public <R extends RegisterResult, I extends RegisterInput> R create (I input)
    {
        RegisterStrategy<R, I> registerStrategy = registerServiceProviderV2.getRegisterStrategy(input.getClass());
        if(registerStrategy == null)
        {
            System.out.println(new NoSuchMethodException("Register strategy is null, check if the strategy has no target type of input to handle"));
            return null;
        }

        registerStrategy.register(input);
        return null;
    }

}
