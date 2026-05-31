package com.practice.__spring_data_mvc_crud_practice.config;

import com.practice.__spring_data_mvc_crud_practice.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

     @Bean
     PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     UserDetailsService userDetailsService(UserService userService) {
          return new CustomUserDetailsService(userService);
     }

     @Bean
     DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService,
               PasswordEncoder passwordEncoder) {
          DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDetailsService);
          dao.setPasswordEncoder(passwordEncoder);

          return dao;
     }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/") // Thêm true nếu muốn luôn force về trang chủ sau khi login
                        .failureUrl("/login?error")
                        .permitAll()
                );
        return http.build();
    }
}
