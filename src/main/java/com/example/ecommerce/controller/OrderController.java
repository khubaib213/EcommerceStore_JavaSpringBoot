package com.example.ecommerce.controller;


import com.example.ecommerce.DTOs.OrderDTO;
import com.example.ecommerce.DTOs.PlaceOrderDTO;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService= orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<OrderDTO> placeOrder(@Valid @RequestBody PlaceOrderDTO request, @AuthenticationPrincipal String email)
    {
        return ResponseEntity.ok(orderService.placeOrder(email, request));
    }

    @GetMapping("/my-orders")
    public ResponseEntity <List<OrderDTO>> getMyOrders(@AuthenticationPrincipal String email)
    {
        return ResponseEntity.ok( orderService.getMyOrders(email));
    }

}
