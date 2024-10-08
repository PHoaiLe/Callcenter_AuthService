package com.callcenter.AuthService.Support.Password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SupportPasswordEncoder
{
    public static int BCRYPT_STRENGTH = 16;

    public String encodeByBcrypt(String plainPassword)
    {
        SecureRandom saltGenerator = new SecureRandom();
        //encode the password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(SupportPasswordEncoder.BCRYPT_STRENGTH, saltGenerator);

        String encodedPassword = passwordEncoder.encode(plainPassword);

        return encodedPassword;
    }
}
