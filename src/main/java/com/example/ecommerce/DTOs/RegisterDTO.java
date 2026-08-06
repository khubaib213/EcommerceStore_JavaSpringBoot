package com.example.ecommerce.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank(message="Email is required")
    @Email(message = "Must be a Valid Email")
    private String email;


    @NotBlank(message = "password is required")
    @Size(min = 6, message = "Password must be greater than 6 characters")
    private String password;

    public RegisterDTO(){};

    public RegisterDTO(String email, String password)
    {
        this.email=email;
        this.password= password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
