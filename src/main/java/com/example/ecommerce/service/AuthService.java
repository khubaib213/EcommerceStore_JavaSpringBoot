package com.example.ecommerce.service;


import com.example.ecommerce.DTOs.LoginDTO;
import com.example.ecommerce.DTOs.RegisterDTO;
import com.example.ecommerce.entity.AuthUser;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.AuthUserRepository;
import com.example.ecommerce.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, JwtService jwtService)
    {
        this.authUserRepository=authUserRepository;
        this.jwtService=jwtService;
        this.passwordEncoder=new BCryptPasswordEncoder();
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

    public String Login(LoginDTO request) {
        AuthUser user = authUserRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found "));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw new RuntimeException("Password doesn't match");
        }
        return jwtService.generateToken(request.getEmail());
    }
}
