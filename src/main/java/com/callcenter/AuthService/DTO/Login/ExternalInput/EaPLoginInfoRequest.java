package com.callcenter.AuthService.DTO.Login.ExternalInput;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public record EaPLoginInfoRequest(
        @NonNull
        String email,
        @NonNull
        String password
) {}
