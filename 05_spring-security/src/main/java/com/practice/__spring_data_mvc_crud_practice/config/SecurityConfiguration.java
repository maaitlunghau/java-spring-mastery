package com.practice.__spring_data_mvc_crud_practice.config;

import com.practice.__spring_data_mvc_crud_practice.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

     /**
      * Creates a BCrypt-based password encoder for hashing and verifying user passwords.
      *
      * @return a PasswordEncoder that uses BCrypt for password encoding and verification
      */
     @Bean
     PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     /**
      * Creates a UserDetailsService backed by the application's UserService.
      *
      * @param userService the service used to load user data for authentication
      * @return a UserDetailsService that loads user details from the provided UserService
      */
     @Bean
     UserDetailsService userDetailsService(UserService userService) {
          return new CustomUserDetailsService(userService);
     }

     /**
      * Creates a DaoAuthenticationProvider wired with the given UserDetailsService and PasswordEncoder.
      *
      * @param userDetailsService the service used to load user-specific data during authentication
      * @param passwordEncoder    the encoder used to verify user credentials
      * @return the configured DaoAuthenticationProvider
      */
     @Bean
     DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,
               PasswordEncoder passwordEncoder) {
          DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDetailsService);
          dao.setPasswordEncoder(passwordEncoder);

          return dao;
     }

    /**
     * Configure and build the application's HTTP security filter chain.
     *
     * Configures request authorization (permits "/", "/login", "/register"; requires role "ADMIN" for
     * "/users", "/user/**", "/products", "/product/**"; requires authentication for all other requests),
     * sets up form login with a custom login page ("/login"), success redirect ("/"), failure redirect
     * ("/login?error"), and configures an access-denied page ("/access-deny").
     *
     * @param http the HttpSecurity builder used to configure the security filter chain
     * @return the configured SecurityFilterChain
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/").permitAll()
                        .requestMatchers("/users", "/user/**", "/products", "/product/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/") // Thêm true nếu muốn luôn force về trang chủ sau khi login
                        .failureUrl("/login?error")
                        .permitAll( )
                );

                http.exceptionHandling(e -> e.accessDeniedPage("/access-deny"));

        return http.build();
    }
}
