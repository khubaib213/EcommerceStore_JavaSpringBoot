package com.example.ecommerce;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService {

    public static final String SECRET = "mystoresecretkeymystoresecretkeymystoresecretkey";
    private static final long EXPIRATION = 1000 * 60 * 60;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String Token)
    {
        return getClaims(Token).getSubject();
    }

    public boolean isTokenValid (String token)
    {
        try{
            return getClaims(token).getExpiration().after(new Date());
        }catch(Exception e)
        {
            return false;
        }
    }
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
