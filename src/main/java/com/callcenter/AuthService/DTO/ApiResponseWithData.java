package com.callcenter.AuthService.DTO;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

@Builder
public class ApiResponseWithData<RESPONSE_BODY> extends ApiResponse<RESPONSE_BODY>
{
    private String message;
    private int statusCode;
    private RESPONSE_BODY data;

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
    public RESPONSE_BODY getData() {
        return this.data;
    }
}
