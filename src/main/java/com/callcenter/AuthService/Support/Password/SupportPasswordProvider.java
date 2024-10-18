package com.callcenter.AuthService.Support.Password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SupportPasswordProvider
{

    private PasswordEncoder passwordEncoder;

    public SupportPasswordProvider(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodeByBcrypt(String plainPassword)
    {

        String encodedPassword = this.passwordEncoder.encode(plainPassword);
        return encodedPassword;
    }

    public boolean matchByBCrypt(String encodedPassword, String rawPassword)
    {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
