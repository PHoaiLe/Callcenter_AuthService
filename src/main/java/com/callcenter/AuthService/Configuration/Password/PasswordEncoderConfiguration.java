package com.callcenter.AuthService.Configuration.Password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordEncoderConfiguration
{
    //the default value is 10;
    private int BCRYPT_STRENGTH = 10;

    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        SecureRandom secureRandom = new SecureRandom();
        return new BCryptPasswordEncoder(this.BCRYPT_STRENGTH, secureRandom);
    }

}
