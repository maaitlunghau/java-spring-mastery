package com.practice.__spring_security_practice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name is required", groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotBlank(message = "Email is required", groups = { OnCreate.class, OnUpdate.class })
    private String email;

    @NotBlank(message = "Password is required", groups = { OnCreate.class })
    private String password;

    @NotBlank(message = "Address is required", groups = { OnCreate.class, OnUpdate.class })
    private String address;

    @NotBlank(message = "Role is required", groups = { OnCreate.class, OnUpdate.class })
    private String role;

    public User() {
    }

    public User(int id, String name, String email, String password, String address, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
