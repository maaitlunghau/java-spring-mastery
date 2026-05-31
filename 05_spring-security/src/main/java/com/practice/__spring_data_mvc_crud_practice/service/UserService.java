package com.practice.__spring_data_mvc_crud_practice.service;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import com.practice.__spring_data_mvc_crud_practice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
 import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository _userRepository;
     private final PasswordEncoder _passwordEncoder;

     /**
      * Create a UserService configured with the given repository and password encoder.
      *
      * @param userRepository repository used for User persistence and queries
      * @param passwordEncoder encoder used to hash user passwords before persistence
      */
     public UserService(UserRepository userRepository, PasswordEncoder
     passwordEncoder) {
     this._userRepository = userRepository;
     this._passwordEncoder = passwordEncoder;
     }

    /**
     * Fetches a paginated list of users, optionally filtering by a keyword that matches name or email.
     *
     * @param keyword an optional search string; when non-null and not blank, only users whose name or email contain this keyword are returned
     * @param page    zero-based page index to retrieve
     * @param size    the number of users per page
     * @return        a Page of User entities for the requested page, filtered by the keyword when provided
     */
    public Page<User> fetchUsersWithSpec(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        if (keyword != null && !keyword.trim().isEmpty()) {
            return this._userRepository.findByNameContainingOrEmailContaining(keyword, keyword, pageable);
        }

        return this._userRepository.findAll(pageable);
    }

    /**
     * Retrieve a user by their email address.
     *
     * @param email the exact email address to look up
     * @return the matching User, or null if no user exists with the given email
     */
    public User findUserByEmail(String email) {
        return this._userRepository.findByEmail(email);
    }

    /**
     * Create a new user account, hashing the provided user's password before persisting.
     *
     * @param user the user entity to save; the plaintext password on this object will be replaced with a hashed password prior to persistence
     * @return the saved User entity
     */
    public User createUser(User user) {
         String hashedPassword = this._passwordEncoder.encode(user.getPassword());
         user.setPassword(hashedPassword);

        return this._userRepository.save(user);
    }

    /**
     * Retrieve a user by its database identifier.
     *
     * @param id the user's database identifier
     * @return an Optional<User> containing the user if found, otherwise empty
     */
    public Optional<User> findUserById(int id) {
        return this._userRepository.findById(id);
    }

    /**
     * Update an existing user in the repository if a user with the same id exists.
     *
     * @param user the user entity to save; the user's id is used to check existence
     * @return the persisted User when the id exists, `null` otherwise
     */
    public User updateUser(User user) {
        if (this._userRepository.existsById(user.getId())) {
            return this._userRepository.save(user);
        }

        return null;
    }

    /**
     * Deletes the user with the given id from the repository.
     *
     * @param id the identifier of the user to delete
     */
    public void deleteUser(int id) {
        this._userRepository.deleteById(id);
    }

    /**
     * Checks whether a user with the given email address exists.
     *
     * @param email the email address to check
     * @return `true` if a user with the given email exists, `false` otherwise
     */
    public boolean isEmailExist(String email) {
        return this._userRepository.existsByEmail(email);
    }

    /**
     * Register a new user by hashing their password, assigning the "USER" role, and persisting the entity.
     *
     * @param user the user to register; its password will be replaced with a hashed value and its role will be set to "USER" before being saved
     */
    public void handleRegister(User user) {
        String hashedPassword = this._passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("USER");

        this._userRepository.save(user);
    }
}
