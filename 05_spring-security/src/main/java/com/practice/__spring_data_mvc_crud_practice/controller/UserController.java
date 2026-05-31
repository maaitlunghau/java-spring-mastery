package com.practice.__spring_data_mvc_crud_practice.controller;

import com.practice.__spring_data_mvc_crud_practice.model.User;
import com.practice.__spring_data_mvc_crud_practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService _userService;

    /**
     * Creates a UserController backed by the given UserService.
     *
     * @param userService the service used to perform user-related operations
     */
    public UserController(UserService userService) {
        this._userService = userService;
    }

    /**
     * Display a paginated list of users, optionally filtered by a search keyword, and populate the model with pagination metadata.
     *
     * @param keyword optional search term to filter users
     * @param page    zero-based page index to display
     * @param size    number of users per page
     * @param model   MVC model to receive the users list and pagination attributes
     * @return        the view name "users/index"
     */
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

    /**
     * Prepare the model for and return the user creation page.
     *
     * @param model the MVC model to which a new empty User is added under the attribute "user"
     * @return the view name for the user creation page ("users/create")
     */
    @GetMapping("/user/create")
    public String createPage(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    /**
     * Handle POST submission to create a new user and redirect to the users index on success.
     *
     * @param user the bound and validated User object to create
     * @param bindingResult validation results for the bound user; if it contains errors the creation is aborted
     * @return the view name to render: {@code "users/create"} when validation fails, otherwise {@code "redirect:/users"}
     */
    @PostMapping("/user/create")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        this._userService.createUser(user);
        return "redirect:/users";
    }

    /**
     * Shows the user update form for the user identified by id.
     *
     * If no user exists with the given id, redirects to the users index.
     *
     * @param id the id of the user to edit
     * @param model the MVC model to populate with the user
     * @return the "users/update" view when the user is found, otherwise a redirect to "/users"
     */
    @GetMapping("/user/update/{id}")
    public String updatePage(@PathVariable int id, Model model) {
        Optional<User> updateUser = this._userService.findUserById(id);
        if (updateUser.isEmpty()) {
            return "redirect:/users";
        }

        model.addAttribute("user", updateUser.get());
        return "users/update";
    }

    /**
     * Handle submission of the user update form: validate input, apply the path id to the bound User, persist updates, and redirect to the users index.
     *
     * @param id the user id from the path to apply to the bound User before updating
     * @param user the bound User instance containing submitted field values
     * @param bindingResult validation results for the bound User; when it contains errors the update is not performed and the update view is returned
     * @return the view name "users/update" when validation errors are present, otherwise a redirect to "/users"
     */
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

    /**
     * Delete the user with the given id and redirect to the users index.
     *
     * @param id the id of the user to delete
     * @return the redirect view name "redirect:/users"
     */
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        this._userService.deleteUser(id);
        return "redirect:/users";
    }

    /**
     * Present the authorization test page for users with the ADMIN role.
     *
     * @return the view path "/test/test-authorize"
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/test-authorize")
    public String testAuthorize() {
        return "/test/test-authorize";
    }
}
