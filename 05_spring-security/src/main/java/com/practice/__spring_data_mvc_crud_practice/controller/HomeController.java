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

    /**
     * Constructs a HomeController that handles home and authentication endpoints.
     *
     * @param userService the service used to validate emails and process user registrations
     */
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Displays the application home view.
     *
     * @return the view name "auth/home"
     */
    @GetMapping("/")
    public String index() {
        return "auth/home";
    }

    /**
     * Displays the access-denied page.
     *
     * @return the view name for the access-denied page
     */
    @GetMapping("/access-deny")
    public String accessDeny() {
        return "/auth/access-deny";
    }

     /**
      * Displays the login page.
      *
      * @return the logical view name "auth/login"
      */
     @GetMapping("/login")
     public String login() {
        return "auth/login";
     }

    /**
     * Prepares the model with an empty User and returns the registration view.
     *
     * @param model the MVC model to populate; this method adds a "user" attribute with a new User instance
     * @return the view name "auth/register"
     */
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    /**
     * Processes registration form submissions: validates the provided user, checks email uniqueness,
     * creates the user on success, and redirects to the login page.
     *
     * @param createUser    the User model populated from the registration form (validated)
     * @param bindingResult the container for validation errors for {@code createUser}
     * @return {@code "auth/register"} when validation fails or the email already exists; {@code "redirect:/login"} after successful registration
     */
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
