package com.example.ecommerce.DTOs;

public class CategoryDTO {

    private int id;
    private String name;
    private String description;

    public CategoryDTO() {
    }

    ;

    public CategoryDTO(int id, String name, String description) {

        this.id=id;
        this.name=name;
        this.description=description;
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



