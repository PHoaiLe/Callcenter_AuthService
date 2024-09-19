package com.callcenter.AuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAsync
@RestController
public class AuthServiceApplication {
	public static void main(String[] args)
	{
		System.out.println(args);
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
