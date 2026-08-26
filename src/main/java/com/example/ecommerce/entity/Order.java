package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String status;
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orders")
    private AuthUser user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }
    public Order(String status, int totalAmount, AuthUser user)
    {
        this.status=status;
        this.totalAmount=totalAmount;
        this.user=user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
