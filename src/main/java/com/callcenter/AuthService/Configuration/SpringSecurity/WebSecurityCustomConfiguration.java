package com.callcenter.AuthService.Configuration.SpringSecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityCustomConfiguration
{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity.authorizeHttpRequests(authorizedRequest ->
                        authorizedRequest
                                .requestMatchers("/apis/v1/auth/register/**").permitAll()
                                .requestMatchers("/apis/v1/auth/login/**").permitAll()
                                .requestMatchers("/apis/v1/auth/welcome").permitAll()
                                .anyRequest().authenticated()
                                )
                .formLogin(formLogin -> formLogin.disable())
                .logout(logout -> logout.disable())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
