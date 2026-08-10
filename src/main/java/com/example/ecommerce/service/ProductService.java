package com.example.ecommerce.service;


import com.example.ecommerce.DTOs.CategoryDTO;
import com.example.ecommerce.DTOs.CreateCategoryDTO;
import com.example.ecommerce.DTOs.CreateProductDTO;
import com.example.ecommerce.DTOs.ProductDTO;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private ProductDTO ConvertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getName()
        );
    }

    public ProductDTO create(CreateProductDTO request) {
        logger.info("Creating product: {}", request.getName());
        Category category = categoryRepository.findById(request.getCategoryID()).orElseThrow(() -> new ResourceNotFoundException("Category with ID:" + request.getCategoryID() + "Not found"));
        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getStock(),
                category
        );
        return ConvertToDTO(productRepository.save(product));
    }

    public List<ProductDTO> getAll() {
        logger.info("Getting all Products");
        return productRepository.findAll()
                .stream()
                .map(this::ConvertToDTO)
                .toList();
    }

    public ProductDTO getByID(int id) {
        logger.info("getting product with :id{}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return ConvertToDTO(product);
    }

    public ProductDTO update(int id, CreateProductDTO request) {
        logger.info("Updating Product with id:{}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        Category category = categoryRepository.findById(request.getCategoryID())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);
        return ConvertToDTO(productRepository.save(product));

    }

    public void delete (int id)
    {
        logger.info("Deleting the product with id:{}", id);
        productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.deleteById(id);
    }
}
