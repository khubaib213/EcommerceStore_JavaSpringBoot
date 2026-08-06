package com.example.ecommerce.security;


import com.example.ecommerce.DTOs.RegisterDTO;
import com.example.ecommerce.entity.AuthUser;
import com.example.ecommerce.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, JwtService jwtService)
    {
        this.authUserRepository=authUserRepository;
        this.jwtService=jwtService;
        this.[passwordEncoder=new BCryptPasswordEncoder();
    }

    public String Register(RegisterDTO request)
    {
        if(authUserRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("Email already Exists");
        }
        String hashedPassword =passwordEncoder.encode(request.getPassword());
        AuthUser user = new AuthUser(request.getEmail(), hashedPassword, "Role_User");
        authUserRepository.save(user);
        return jwtService.generateToken(request.getEmail());
    }



}
