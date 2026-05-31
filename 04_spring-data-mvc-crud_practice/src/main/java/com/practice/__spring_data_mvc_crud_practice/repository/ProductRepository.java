package com.practice.__spring_data_mvc_crud_practice.repository;

import com.practice.__spring_data_mvc_crud_practice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName (String name);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
