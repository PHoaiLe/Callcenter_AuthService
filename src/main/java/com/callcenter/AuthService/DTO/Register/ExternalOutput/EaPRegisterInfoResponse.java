package com.callcenter.AuthService.DTO.Register.ExternalOutput;

import com.callcenter.AuthService.Constants.Register.RegisterStatusEnum;
import com.callcenter.AuthService.DTO.APIResponse;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class EaPRegisterInfoResponse extends APIResponse<RegisterStatusEnum>
{
    public EaPRegisterInfoResponse(RegisterStatusEnum statusEnum)
    {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }

    public EaPRegisterInfoResponse(HttpStatusCode statusCode, String message)
    {
        super(statusCode, message);
    }

    @Override
    public void setStatusAndMessage(RegisterStatusEnum statusEnum)
    {
        super.setStatusCode(statusEnum.getStatusCode());
        super.setMessage(statusEnum.getMessage());
    }

    public void setMessage(String message) {
        super.setMessage(message);
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        super.setStatusCode(statusCode);
    }

    public void setStatusCode(int statusCode)
    {
        super.setStatusCode(statusCode);
    }

    public int getStatusCode()
    {
        return super.getStatusCode();
    }

    public String getMessage()
    {
        return super.getMessage();
    }
}
