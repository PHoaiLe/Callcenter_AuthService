package com.callcenter.AuthService.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public abstract class APIResponse<TYPE_OF_RESPONSE_ENUM>
{
    private int statusCode;
    private String message;

    private APIResponse() {}

    protected APIResponse(HttpStatusCode statusCode, String message)
    {
        this.statusCode = statusCode.value();
        this.message = message;
    }

    protected APIResponse(int statusCode, String message)
    {
        this.statusCode = statusCode;
        this.message = message;
    }

    protected void setStatusCode(int statusCode)
    {
        this.statusCode = statusCode;
    }

    protected void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode.value();
    }

    protected String getMessage()
    {
        return this.message;
    }

    protected int getStatusCode()
    {
        return this.statusCode;
    }

    public abstract void setStatusAndMessage(TYPE_OF_RESPONSE_ENUM statusEnum);
}
