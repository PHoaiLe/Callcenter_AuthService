package com.callcenter.AuthService.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ServiceResult<T>
{
    protected boolean isSuccess;
    protected T object;

    public ServiceResult()
    {
        this.isSuccess = false;
        this.object = null;
    }

    public ServiceResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
        this.object = null;
    }

    public ServiceResult(boolean isSuccess, T object)
    {
        this.isSuccess = isSuccess;
        this.object = object;
    }

}
