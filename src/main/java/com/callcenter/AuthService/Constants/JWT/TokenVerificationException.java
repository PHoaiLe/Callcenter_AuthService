package com.callcenter.AuthService.Constants.JWT;

public class TokenVerificationException extends Exception
{
    public static TokenVerificationException EXPIRED_TOKEN = new TokenVerificationException(TokenVerificationStatusEnum.EXPIRED_TOKEN);
    public static TokenVerificationException UNSUPPORTED_JWT_TOKEN = new TokenVerificationException(TokenVerificationStatusEnum.UNSUPPORTED_JWT);
    public static TokenVerificationException MALFORMED_JWT = new TokenVerificationException(TokenVerificationStatusEnum.MALFORMED_JWT);
    public static TokenVerificationException INVALID_SIGNATURE = new TokenVerificationException(TokenVerificationStatusEnum.INVALID_SIGNATURE);
    public static TokenVerificationException ILLEGAL_ARGUMENTS = new TokenVerificationException(TokenVerificationStatusEnum.ILLEGAL_ARGUMENTS);
    public static TokenVerificationException INVALID_AUTHORIZATION_INFO = new TokenVerificationException(TokenVerificationStatusEnum.INVALID_AUTHORIZATION_INFO);
    public static TokenVerificationException FORBIDDEN_PERMISSION_ACCESS = new TokenVerificationException(TokenVerificationStatusEnum.FORBIDDEN_PERMISSION_ACCESS);



    private TokenVerificationStatusEnum value;

    private TokenVerificationException(TokenVerificationStatusEnum value)
    {
        this.value = value;
    }

    public TokenVerificationStatusEnum getValue()
    {
        return this.value;
    }
}
