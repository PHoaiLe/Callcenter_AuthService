package com.callcenter.AuthService.DTO.Register.ExternalInput;

import lombok.NonNull;

public record EaPRegisterInfoRequest(
        @NonNull
        String email,
        @NonNull
        String password,
        @NonNull
        String roleValue
) {}
