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
    private String address;

    private String role;

    /**
 * Creates a new User instance with fields left unset for use by JPA and other
 * serialization/deserialization frameworks.
 */
public User() {}

    /**
     * Create a User instance with all fields initialized.
     *
     * @param id the user's database identifier
     * @param name the user's full name
     * @param email the user's email address
     * @param password the user's password
     * @param address the user's postal address
     * @param role the user's role or authority label
     */
    public User(int id, String name, String email, String password, String address, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    /**
     * Returns the user's database identifier.
     *
     * @return the user's id
     */
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

    /**
     * Set the user's email address.
     *
     * @param email the email address to assign to this user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to assign to this user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the user's postal or mailing address.
     *
     * @return the user's address as a String, or null if no address is set
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     *
     * @param address the address to assign to the user
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the user's role.
     *
     * @return the user's role, or {@code null} if not set
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role to assign to the user
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * String representation of the User including id, name, email, password, address, and role.
     *
     * @return a string containing the user's id, name, email, password, address, and role in the format "User{...}"
     */
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
