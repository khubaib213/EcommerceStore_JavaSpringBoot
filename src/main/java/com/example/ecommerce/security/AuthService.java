package com.example.ecommerce.security;


import com.example.ecommerce.entity.AuthUser;
import com.example.ecommerce.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(AuthUserRepository authUserRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.authUserRepository=authUserRepository;
        this.jwtService=jwtService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public String Register()


}
