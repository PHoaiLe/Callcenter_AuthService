package com.callcenter.AuthService.DTO;


import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Builder(toBuilder = true)
@Getter
public class ApiResponseWithoutData extends ApiResponse
{
    private int statusCode;
    private String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getIntStatusCode() {
        return this.statusCode;
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return HttpStatusCode.valueOf(this.statusCode);
    }

    @Override
    public Object getData()
    {
        return null;
    }


}
