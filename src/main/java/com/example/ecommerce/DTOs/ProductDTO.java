package com.example.ecommerce.DTOs;

public class ProductDTO {

    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;

    public ProductDTO(){};

    public ProductDTO(int id, String name, String description, double price, int stock, String category)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.price=price;
        this.stock=stock;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCategory(String category) {
        this.category = category;
    }
}

