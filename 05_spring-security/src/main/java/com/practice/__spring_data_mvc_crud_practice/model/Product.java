package com.practice.__spring_data_mvc_crud_practice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required!")
    private String name;

    @Min(value = 0, message = "Price must be greater than or equal to 0!")
    private double price;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    /**
     * Constructs a new Product instance.
     *
     * Required by JPA and persistence frameworks for entity instantiation via reflection.
     */
    public Product() {
    }

    /**
     * Create a Product with the specified quantity, price, name, and id.
     *
     * @param quantity the quantity of the product (must be zero or greater)
     * @param price    the unit price of the product (must be zero or greater)
     * @param name     the product name; must not be blank
     * @param id       the primary key identifier for the product
     */
    public Product(int quantity, double price, String name, int id) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    // --- Các hàm Getter và Setter bên dưới hãy xóa bỏ toàn bộ các Annotation
    /**
     * Gets the product's identifier.
     *
     * @return the product's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the product's primary key identifier.
     *
     * @param id the primary key identifier to assign to this product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the product's name.
     *
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the product's name.
     *
     * @param name the product name; must not be blank (validated by the entity)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product's price.
     *
     * @return the product's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product's price.
     *
     * @param price the price to assign; must be greater than or equal to 0
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the product's quantity in stock.
     *
     * @return the product's quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the product's quantity.
     *
     * @param quantity the quantity of the product; should be greater than or equal to 0
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Provide a string representation of the product including its id, name, price, and quantity.
     *
     * @return a string in the format Product{id=..., name='...', price=..., quantity=...} containing the product's id, name, price, and quantity
     */
    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}