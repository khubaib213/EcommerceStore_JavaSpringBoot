package com.example.ecommerce.repository;

import com.example.ecommerce.entity.AuthUser;
import jakarta.security.auth.message.AuthStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByEmail (String email);
}
