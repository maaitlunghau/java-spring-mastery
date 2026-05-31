package com.practice.__spring_data_mvc_crud_practice.controller;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import com.practice.__spring_data_mvc_crud_practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    @GetMapping("/users")
    public String index(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size,
            Model model) {

        Page<User> userPage = this._userService.fetchUsersWithSpec(keyword, page, size);

        model.addAttribute("users", userPage.getContent());

        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("totalItems", userPage.getTotalElements());
        model.addAttribute("size", size);

        return "users/index";
    }

    @GetMapping("/user/create")
    public String createPage(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    @PostMapping("/user/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        this._userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user/update/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        Optional<User> updateUser = this._userService.findUserById(id);
        if (updateUser.isEmpty()) {
            return "redirect:/users";
        }

        model.addAttribute("user", updateUser.get());
        return "users/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(
            @PathVariable int id,
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "users/update";
        }

        user.setId(id);
        this._userService.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        this._userService.deleteUser(id);
        return "redirect:/users";
    }
}
