package com.callcenter.AuthService.Constants.Register;

public class RegisterException extends Exception
{

    public final static RegisterException EMAIL_ALREADY_USED = new RegisterException(RegisterStatusEnum.EMAIL_ALREADY_USED);
    public final static RegisterException UNRECOGNIZED_ROLE_VALUE = new RegisterException(RegisterStatusEnum.UNRECOGNIZED_ROLE_VALUE);
    public final static RegisterException UNRECOGNIZED_REGISTER_TYPE = new RegisterException(RegisterStatusEnum.UNRECOGNIZED_REGISTER_TYPE);
    public final static RegisterException VIOLATION_IN_CREDENTIALS_TO_CREATE_ACCOUNT = new RegisterException(RegisterStatusEnum.UNRECOGNIZED_REGISTER_TYPE);
    public final static RegisterException UNSUPPORTED_REGISTER_STRATEGY = new RegisterException(RegisterStatusEnum.UNSUPPORTED_REGISTER_STRATEGY);

    private RegisterStatusEnum value;

    private RegisterException(RegisterStatusEnum value)
    {
        this.value = value;
    }

    public RegisterStatusEnum getValue() {
        return value;
    }
}
