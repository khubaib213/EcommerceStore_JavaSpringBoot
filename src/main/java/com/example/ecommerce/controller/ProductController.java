package com.example.ecommerce.controller;


import com.example.ecommerce.DTOs.CreateProductDTO;
import com.example.ecommerce.DTOs.ProductDTO;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductDTO request) {
        return ResponseEntity.ok(productService.create(request));
    }

    @PostMapping("/all")
    public ResponseEntity<List<ProductDTO>>getAll()
    {
        return ResponseEntity.ok(productService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable int id)
    {
        return ResponseEntity.ok(productService.getByID(id));
    }

}
