package com.callcenter.AuthService.DTO.SignIn.ExternalInput;

import lombok.NonNull;

public record EaPSignInInfoRequest(
        @NonNull
        String email,
        @NonNull
        String password
) {}
