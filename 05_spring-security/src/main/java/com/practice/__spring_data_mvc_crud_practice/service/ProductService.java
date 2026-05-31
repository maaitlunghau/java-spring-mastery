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

    /**
     * Creates a ProductService that delegates product persistence operations to the provided repository.
     *
     * @param productRepository the repository used for Product data access
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieve all products available in the repository.
     *
     * @return list of all products
     */
    public List<Product> fetchAllProducts() {
        return this.productRepository.findAll();
    }

    /**
     * Retrieves a page of products, optionally filtering by name containing the given keyword (case-insensitive).
     *
     * @param keyword filter substring for product name; if null or blank no filtering is applied
     * @param page zero-based page index
     * @param size page size (number of items per page)
     * @return a page of products ordered by id descending; filtered by name when keyword is provided
     */
    public Page<Product> fetchProductsWithSpec(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        if (keyword != null && !keyword.trim().isEmpty()) {
            return this.productRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }

        return this.productRepository.findAll(pageable);
    }

    /**
     * Retrieve a product by its identifier.
     *
     * @param id the product's identifier
     * @return an Optional containing the product when found, empty otherwise
     */
    public Optional<Product> findProductById(int id) {
        return this.productRepository.findById(id);
    }

    /**
     * Persist the given product and return the saved instance.
     *
     * @param pro the product to create and persist
     * @return the persisted Product with any persistence-generated fields (e.g., id) populated
     */
    public Product createProduct(Product pro) {
        return this.productRepository.save(pro);
    }

    /**
     * Updates an existing product by saving the provided entity.
     *
     * @param pro the Product to update; its id must reference an existing product
     * @return the updated Product
     * @throws RuntimeException if no product exists with the provided id
     */
    public Product updateProduct(Product pro) {
        if (!this.productRepository.existsById(pro.getId())) {
            throw new RuntimeException("Product with ID " + pro.getId() + " not found!");
        }
        return this.productRepository.save(pro);
    }

    /**
     * Deletes the product with the given id.
     *
     * @param id the id of the product to delete
     * @throws RuntimeException if no product with the given id exists (message: "Product with ID <id> not found!")
     */
    public void deleteProduct(int id) {
        if (!this.productRepository.existsById(id)) {
            throw new RuntimeException("Product with ID " + id + " not found!");
        }
        this.productRepository.deleteById(id);
    }
}