package com.callcenter.AuthService.DTO.TokenVerification;

import com.callcenter.AuthService.Constants.JWT.TokenVerificationStatusEnum;
import com.callcenter.AuthService.DTO.APIResponse;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@Data
public class TokenVerificationResponse extends APIResponse<TokenVerificationStatusEnum>
{
    private String key;
    private Integer role;
    private String targetPermission;

    public TokenVerificationResponse(HttpStatusCode statusCode, String message, String key, Integer role, String targetPermission) {
        super(statusCode, message);
        this.key = key;
        this.role = role;
        this.targetPermission = targetPermission;
    }

    public TokenVerificationResponse(TokenVerificationStatusEnum statusEnum)
    {
        super(statusEnum.getStatusCode(), statusEnum.getMessage());
    }

    public TokenVerificationResponse(HttpStatusCode statusCode, String message)
    {
        super(statusCode, message);
    }

    @Override
    public void setStatusAndMessage(TokenVerificationStatusEnum statusEnum) {
        super.setStatusCode(statusEnum.getStatusCode());
        super.setMessage(statusEnum.getMessage());
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }

    @Override
    public int getStatusCode()
    {
        return super.getStatusCode();
    }
}
