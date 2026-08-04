package com.example.ecommerce;


import org.springframework.stereotype.Component;

@Component
public class JwtFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService)
    {
        this.jwtService=jwtService;
    }
}
