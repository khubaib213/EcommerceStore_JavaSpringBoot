package com.example.ecommerce.service;

import com.example.ecommerce.DTOs.OrderDTO;
import com.example.ecommerce.DTOs.OrderItemDTO;
import com.example.ecommerce.DTOs.PlaceOrderDTO;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.AuthUserRepository;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.lang.model.element.ModuleElement;
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

    private OrderDTO convertToDTOWithItems(Order order, List<OrderItem> OrderItems) {
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

    public OrderDTO placeOrder(String email, PlaceOrderDTO request) {
        logger.info("Placing Order for: {}", email);

        AuthUser user = authUserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order("Pending", 0.0, user);
        orderRepository.save(order);

        double TotalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product Not Found " + itemRequest.getProductId()));
            if (product.getStock() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insuffecient Stock for : " + product.getName());
            }
            double itemPrice = product.getPrice() * itemRequest.getQuantity();
            OrderItem orderItem = new OrderItem(
                    itemRequest.getQuantity(),
                    itemPrice,
                    order,
                    product
            );
            orderItems.add(orderItem);
            TotalAmount += itemPrice;

            product.setStock(product.getStock() - itemRequest.getQuantity());
            productRepository.save(product);

        }
        orderItemRepository.saveAll(orderItems);
        order.setTotalAmount(TotalAmount);
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        logger.info("Order placed with id: {}", order.getId());

        return convertToDTOWithItems(order, orderItems);
    }

    public List<OrderDTO> getMyOrders(String userEmail) {
        logger.info("Fetching Order with email: {}", userEmail);
        return orderRepository.findByEmail(userEmail)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public OrderDTO getOrderById(int userId) {
        logger.info("Fetching Orders with User ID: {}", userId);
        Order order = orderRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Order not found against id: " + userId));
        return convertToDTO(order);
    }

    public OrderDTO CancelOrderById(int id, String email) {
        logger.info("Cancelling the order with ID: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        if (!order.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only cancel your own orders");
        }

        if(order.getStatus().equals("CANCELLED"))
        {
            throw new RuntimeException("Order already cancelled");
        }

        order.setStatus("CANCELLED");

        if(order.getOrderItems() != null)
        {
            for(OrderItem item: order.getOrderItems())
            {
                Product product = item.getProduct();
                product.setStock(product.getStock() + item.getQuantity());
                productRepository.save(product);
            }
        }

        return convertToDTO(orderRepository.save(order));
    }
}
