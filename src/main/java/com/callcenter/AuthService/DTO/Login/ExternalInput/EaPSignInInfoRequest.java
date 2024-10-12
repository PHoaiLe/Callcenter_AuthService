package com.callcenter.AuthService.DTO.Login.ExternalInput;

import lombok.NonNull;

public record EaPSignInInfoRequest(
        @NonNull
        String email,
        @NonNull
        String password
) {}
