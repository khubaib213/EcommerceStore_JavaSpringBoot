package com.example.ecommerce;


import io.jsonwebtoken.Jwt;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter)
    {
        this.jwtFilter=jwtFilter;
    }

}
