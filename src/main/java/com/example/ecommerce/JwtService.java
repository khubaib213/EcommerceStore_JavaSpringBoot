package com.example.ecommerce;


import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public static final String SECRET = "mystoresecretkeymystoresecretkeymystoresecretkey";
    private static final long EXPIRATION = 1000 * 60 * 60;
}
