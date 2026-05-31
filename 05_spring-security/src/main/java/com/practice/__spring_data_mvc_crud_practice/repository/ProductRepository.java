package com.practice.__spring_data_mvc_crud_practice.repository;

import com.practice.__spring_data_mvc_crud_practice.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
 * Finds a product by its exact name.
 *
 * @param name the exact product name to search for
 * @return an Optional containing the matching Product, or empty if no match is found
 */
Optional<Product> findByName (String name);
    /**
 * Finds products whose name contains the given substring using case-insensitive matching and applies pagination.
 *
 * @param name     the substring to search for within product names
 * @param pageable pagination and sorting information to apply to the result
 * @return a Page of Product entities whose name contains the provided substring (case-insensitive), according to the supplied Pageable
 */
Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
