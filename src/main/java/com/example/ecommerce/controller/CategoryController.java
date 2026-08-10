package com.example.ecommerce.controller;


import com.example.ecommerce.DTOs.CategoryDTO;
import com.example.ecommerce.DTOs.CreateCategoryDTO;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CreateCategoryDTO request)
    {
        return ResponseEntity.ok(categoryService.create(request));
    }
}
