package com.callcenter.AuthService.DTO.Register;

import com.callcenter.AuthService.DTO.ServiceResult;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Data
@Getter
@Setter
public class RegisterResult extends ServiceResult
{
    public RegisterResult()
    {
        super();
    }

    public RegisterResult(boolean isSuccess)
    {
        super(isSuccess);
    }

    public RegisterResult(boolean isSuccess, Object object)
    {
        super(isSuccess, object);
    }

    @Override
    public void setSuccess(boolean isSuccess) {
        super.setSuccess(isSuccess);
    }

    @Override
    public void setObject(Object object) {
        super.setObject(object);
    }
}
