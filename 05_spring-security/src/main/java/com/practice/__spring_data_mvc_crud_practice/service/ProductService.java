package com.practice.__spring_data_mvc_crud_practice.service;

import com.practice.__spring_data_mvc_crud_practice.model.Product;
import com.practice.__spring_data_mvc_crud_practice.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> fetchAllProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> fetchProductsWithSpec(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        if (keyword != null && !keyword.trim().isEmpty()) {
            return this.productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

        return this.productRepository.findAll(pageable);
    }

    public Optional<Product> findProductById(int id) {
        return this.productRepository.findById(id);
    }

    public Product createProduct(Product pro) {
        return this.productRepository.save(pro);
    }

    public Product updateProduct(Product pro) {
        if (!this.productRepository.existsById(pro.getId())) {
            throw new RuntimeException("Product with ID " + pro.getId() + " not found!");
        }
        return this.productRepository.save(pro);
    }

    public void deleteProduct(int id) {
        if (!this.productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " not found!");
        }
        this.productRepository.deleteById(id);
    }
}