package com.example.ecommerce;


import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService {

    public static final String SECRET = "mystoresecretkeymystoresecretkeymystoresecretkey";
    private static final long EXPIRATION = 1000 * 60 * 60;

    private Key getSigningKey()
    {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken ()
}
