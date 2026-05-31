package com.practice.__spring_data_mvc_crud_practice.controller;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import com.practice.__spring_data_mvc_crud_practice.service.UserService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "auth/home";
    }

    @GetMapping("/access-deny")
    public String accessDeny() {
        return "/auth/access-deny";
    }

     @GetMapping("/login")
     public String login() {
        return "auth/login";
     }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute User createUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        if (this.userService.isEmailExist(createUser.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "Email đã tồn tại, vui lòng sử dụng email khác.");
            return "auth/register";
        }

        this.userService.handleRegister(createUser);

        return "redirect:/login";
    }
}
