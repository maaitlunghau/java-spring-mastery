package com.practice.__spring_data_mvc_crud_practice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Address is required")
    private String Address;

    @NotBlank(message = "Role is required")
    private String role;

    public User() {}

    public User(int id, String name, String email, String password, String address, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.Address = address;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return Address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        Address = address;
    }

    public @NotBlank(message = "role is required") String getrole() {
        return role;
    }

    public void setrole(@NotBlank(message = "role is required") String role) {
        role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Address='" + Address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
