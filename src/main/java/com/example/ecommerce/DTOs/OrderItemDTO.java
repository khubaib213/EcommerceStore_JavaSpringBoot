package com.example.ecommerce.DTOs;

import com.example.ecommerce.entity.Order;

public class OrderItemDTO {

    private int id;
    private String ProductName;
    private int quantity;
    private double price;

    public OrderItemDTO(){};

    public OrderItemDTO(int id, String ProductName, int quantity, double price)
    {
        this.id=id;
        this.ProductName=ProductName;
        this.quantity=quantity;
        this.price=price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}
