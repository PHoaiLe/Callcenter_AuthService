package com.callcenter.AuthService.DTO.Endpoint;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class APIEndPoint
{
    private APIMethods httpMethod;
    private String path;

    private static String SEPARATOR_OF_ENDPOINT_ATTRIBUTE = "::";

    private APIEndPoint(APIMethods httpMethod, String path)
    {
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public String toDatabaseEndpointAttribute()
    {
        return new StringBuilder()
                .append(this.httpMethod.getMethod())
                .append(SEPARATOR_OF_ENDPOINT_ATTRIBUTE)
                .append(path)
                .toString();
    }
}
