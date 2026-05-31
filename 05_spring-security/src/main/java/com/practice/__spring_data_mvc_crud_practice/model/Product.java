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

    public Product() {
    }

    public Product(int quantity, double price, String name, int id) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.id = id;
    }

    // --- Các hàm Getter và Setter bên dưới hãy xóa bỏ toàn bộ các Annotation
    // @NotBlank thừa đi ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}