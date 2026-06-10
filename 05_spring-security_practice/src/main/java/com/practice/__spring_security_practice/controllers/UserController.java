package com.practice.__spring_security_practice.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.practice.__spring_security_practice.models.User;
import com.practice.__spring_security_practice.services.UserService;

import jakarta.validation.Valid;

// 1. phân biệt sự khác nhau giữa: return "users/index" và return "redirect:/users"
// - return "users/index
// + 
// +
// +
// +
// - return "redirect:/users"
// +
// +
// +
// +

// 2. ?

@Controller
public class UserController {

    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    // view
    @GetMapping("/users")
    public String index(Model model) {
        List<User> userList = this._userService.getAllUsers();

        model.addAttribute("users", userList);
        return "user/index";
    }

    @GetMapping("/users/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @GetMapping("/users/update")
    public String update() {
        return "user/update";
    }

    // create
    @PostMapping("/users/create")
    public String createUser(@Valid @ModelAttribute User createUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/create";

        this._userService.createUser(createUser);
        return "redirect:/users";
    }

    // update

    // delete
}
