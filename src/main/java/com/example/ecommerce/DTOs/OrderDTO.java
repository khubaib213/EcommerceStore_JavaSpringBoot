package com.example.ecommerce.DTOs;

import java.util.List;

public class OrderDTO {

    private int id;
    private String status;
    private double totalAmount;
    private String userEmail;
    private List<OrderItemDTO> items;


    public OrderDTO(){};

    public OrderDTO(int id, String status, double totalAmount, String userEmail, List<OrderItemDTO> items)
    {
        this.id=id;
        this.status=status;
        this.totalAmount=totalAmount;
        this.userEmail=userEmail;
        this.items=items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

}
