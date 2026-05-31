package com.practice.__spring_data_mvc_crud_practice.config;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import com.practice.__spring_data_mvc_crud_practice.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * Create a CustomUserDetailsService that locates users for authentication using the provided service.
     *
     * @param userService the service used to find users (by email) and obtain their credentials and role
     */
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Loads a user's Spring Security UserDetails by locating the application user with the given email.
     *
     * @param username the email address used to find the user
     * @return a UserDetails populated with the user's email, password, and a single authority prefixed with "ROLE_"
     * @throws UsernameNotFoundException if no user exists for the given email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.practice.__spring_data_mvc_crud_practice.model.User myUser = this.userService.findUserByEmail(username);

        if (myUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                myUser.getEmail(),
                myUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + myUser.getRole()))
        );
    }
}

