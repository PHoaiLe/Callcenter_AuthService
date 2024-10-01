package com.callcenter.AuthService.DTO.Register.ExternalOutput;

import com.callcenter.AuthService.DTO.APIResponse;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

@Data
public class EaPRegisterInfoResponse extends APIResponse
{
    public EaPRegisterInfoResponse(HttpStatusCode statusCode, String message)
    {
        super(statusCode, message);
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
