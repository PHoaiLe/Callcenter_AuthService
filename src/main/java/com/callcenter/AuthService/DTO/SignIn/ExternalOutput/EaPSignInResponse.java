package com.callcenter.AuthService.DTO.SignIn.ExternalOutput;

import com.callcenter.AuthService.Constants.SignIn.SignInStatusEnum;
import com.callcenter.AuthService.DTO.APIResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Data
@Getter
@Setter
public class EaPSignInResponse extends APIResponse<SignInStatusEnum>
{
    private String accessToken;
    private Date accessTokenExpiration;
    private String refreshToken;
    private Date refreshTokenExpiration;

    public EaPSignInResponse(SignInStatusEnum statusEnum)
    {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }

    @Override
    public void setStatusAndMessage(SignInStatusEnum statusEnum)
    {
        super.setStatusCode(statusEnum.getStatusCode());
        super.setMessage(statusEnum.getMessage());
    }

    public EaPSignInResponse(HttpStatusCode statusCode, String message) {
        super(statusCode, message);
    }

    public EaPSignInResponse(int statusCode, String message) {
        super(statusCode, message);
    }

    public int getStatusCode()
    {
        return super.getStatusCode();
    }

    public String getMessage()
    {
        return super.getMessage();
    }

    public void setStatusCode(int statusCode)
    {
        super.setStatusCode(statusCode);
    }

    public void setMessage(String message)
    {
        super.setMessage(message);
    }

    public void setStatusCode(HttpStatusCode statusCode)
    {
        super.setStatusCode(statusCode);
    }
}
