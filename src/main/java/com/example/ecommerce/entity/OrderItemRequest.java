package com.example.ecommerce.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotNull(message = "Product ID is required")
    private Integer ProductId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public OrderItemRequest(){};

    public Integer getProductId() {
        return ProductId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
