package com.callcenter.AuthService.DTO.Register.ExternalInput;

public record EaPRegisterInfoRequest(
   String email,
   String password,
   String roleValue
) {}
