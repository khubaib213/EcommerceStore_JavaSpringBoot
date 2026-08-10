package com.example.ecommerce.controller;


import com.example.ecommerce.DTOs.CategoryDTO;
import com.example.ecommerce.DTOs.CreateCategoryDTO;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>>findAll()
    {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO>getById(@PathVariable int id)
    {
        return ResponseEntity.ok(categoryService.getByID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO>update(@PathVariable int id, @Valid @RequestBody CreateCategoryDTO request)
    {
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable int id)
    {
        categoryService.delete(id);
        return ResponseEntity.ok("Category Deleted Successfully");
    }

}
