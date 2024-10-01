package com.callcenter.AuthService.Services.RegisterService;

import com.callcenter.AuthService.DTO.Register.RegisterStrategyResult;
import com.callcenter.AuthService.DTO.Register.RegisterInput;

public abstract class RegisterStrategy<R extends RegisterStrategyResult, I extends RegisterInput>
{
    protected String key = null;
    protected Class<I> inputClassToHandle;

    protected RegisterStrategy()
    {
        this.key = null;
        inputClassToHandle = null;
    }

    protected RegisterStrategy(String key)
    {
        this.key = key;
    }

    protected RegisterStrategy(Class<I> inputClassToHandle)
    {
        this.inputClassToHandle = inputClassToHandle;
    }

    protected RegisterStrategy(String key, Class<I> inputClassToHandle)
    {
        this.key = key;
        this.inputClassToHandle = inputClassToHandle;
    }

    protected String getKey() {
        return this.key;
    }

    protected void setKey(String key)
    {
        this.key = key;
    }

    protected Class<I> getInputClassToHandle() {
        return inputClassToHandle;
    }

    protected void setInputClassToHandle(Class<I> inputClassToHandle) {
        this.inputClassToHandle = inputClassToHandle;
    }

    abstract public RegisterStrategy initializes();
    abstract public R register(I input);
    abstract public void rollback(String recordId);
}
