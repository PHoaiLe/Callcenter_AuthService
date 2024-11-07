package com.callcenter.AuthService.DTO.Endpoint;

public enum APIMethods
{
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private String method;

    private APIMethods(String method)
    {
        this.method = method;
    }

    public String getMethod()
    {
        return this.method;
    }

    public static APIMethods toMethod(String string)
    {
        String formatedString = string.toUpperCase().trim();

        if(formatedString.compareTo(GET.getMethod()) == 0)
        {
            return APIMethods.GET;
        }
        else if(formatedString.compareTo(APIMethods.POST.getMethod()) == 0)
        {
            return APIMethods.POST;
        }
        else if(formatedString.compareTo(APIMethods.PUT.getMethod()) == 0)
        {
            return APIMethods.PUT;
        }
        else if(formatedString.compareTo(APIMethods.DELETE.getMethod()) == 0)
        {
            return APIMethods.DELETE;
        }
        else if(formatedString.compareTo(APIMethods.PATCH.getMethod()) == 0)
        {
            return APIMethods.PATCH;
        }

        return null;
    }

}
