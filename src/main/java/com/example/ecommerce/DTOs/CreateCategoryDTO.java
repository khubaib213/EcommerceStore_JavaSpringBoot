package com.example.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;

public class CreateCategoryDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    public CreateCategoryDTO(){};

    public CreateCategoryDTO(String name, String description)
    {
        this.name=name;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
