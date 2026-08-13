package com.example.ecommerce.service;

import ch.qos.logback.core.util.DefaultInvocationGate;
import com.example.ecommerce.DTOs.CategoryDTO;
import com.example.ecommerce.DTOs.CreateCategoryDTO;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }

    private CategoryDTO convertToDTO(Category category)
    {
       return new CategoryDTO(
               category.getId(),
               category.getName(),
               category.getDescription()
       );
    }

    public CategoryDTO create(CreateCategoryDTO request)
    {
        logger.info("Creating category:{}", request.getName());
        Category category = new Category(request.getName(), request.getDescription());
        return convertToDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAll()
    {
        logger.info("Fetching all categories");
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CategoryDTO getByID(int id)
    {
        logger.info("Fetching the categoru by ID:{}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id:"+id));
        return convertToDTO(category);
    }

    public CategoryDTO update(int id, CreateCategoryDTO request)
    {
        logger.info("Updating the catrgory with ID:{}",id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id:"+id));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return convertToDTO(categoryRepository.save(category));
    }

    public void delete(int id)
    {
        logger.info("Deleting the category with id:{}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category not found with id:"+id));
        categoryRepository.deleteById(id);
    }
}
