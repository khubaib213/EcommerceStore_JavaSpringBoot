package com.example.ecommerce.service;

import com.example.ecommerce.DTOs.OrderDTO;
import com.example.ecommerce.DTOs.OrderItemDTO;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.OrderItem;
import com.example.ecommerce.repository.AuthUserRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AuthUserRepository authUserRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, AuthUserRepository authUserRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.authUserRepository = authUserRepository;
        this.orderItemRepository = orderItemRepository;
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> items = order.getOrderItems() == null
                ? new ArrayList<>()
                : order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                )).toList();

        return new OrderDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getUser().getEmail(),
                items
        );
    }

    private OrderDTO convertToDTOWithItems (Order order, List<OrderItem> OrderItems)
    {
        List<OrderItemDTO> items = OrderItems.stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        return new OrderDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getUser().getEmail(),
                items);
    }
}
