package com.example.ecommerce.controller;

import com.example.ecommerce.DTOs.LoginDTO;
import com.example.ecommerce.DTOs.RegisterDTO;
import com.example.ecommerce.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService)
    {
        this.authService= authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO request)
    {
        String token = authService.Register(request);
        return ResponseEntity.ok(Map.of("token",token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginDTO request)
    {
        String token = authService.Login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
