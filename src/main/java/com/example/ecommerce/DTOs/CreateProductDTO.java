package com.example.ecommerce.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateProductDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private double price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    public CreateProductDTO (){};

    public CreateProductDTO(String name, String description, double price, int stock, Integer categoryId)
    {
        this.name=name;
        this.description=description;
        this.stock=stock;
        this.price=price;
        this.categoryId=categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public Integer getCategoryID() {
        return categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryId = categoryID;
    }
}
