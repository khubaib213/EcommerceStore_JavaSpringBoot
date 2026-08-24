package com.example.ecommerce.DTOs;

import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.entity.OrderItemRequest;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class PlaceOrderDTO {


    @NotEmpty(message = "Order must not be empty")
    private List<OrderItemRequest> items;

    public PlaceOrderDTO(){};

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

}
