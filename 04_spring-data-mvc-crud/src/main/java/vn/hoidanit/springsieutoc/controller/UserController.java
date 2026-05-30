/*
 * Author: Hỏi Dân IT - @hoidanit 
 *
 * This source code is developed for the course
 * "Java Spring Siêu Tốc - Tự Học Java Spring Từ Số 0 Dành Cho Beginners từ A tới Z".
 * It is intended for educational purposes only.
 * Unauthorized distribution, reproduction, or modification is strictly prohibited.
 *
 * Copyright (c) 2025 Hỏi Dân IT. All Rights Reserved.
 */

package vn.hoidanit.springsieutoc.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.springsieutoc.model.User;
import vn.hoidanit.springsieutoc.service.UserService;

@Controller // MVC
public class UserController {

	private final UserService _userService;

	public UserController(UserService userService) {
		this._userService = userService;
	}

	@GetMapping("/template-engine")
	public String testTemplateEngine(Model model) {
		model.addAttribute("name", "maaitlunghau");
		return "hello.html";
	}

	@GetMapping("/admin")
	public String showAdmin() {
		return "/admin/showAdmin";
	}

	@GetMapping("/user")
	public String showUser(Model model) {
		// 1. HARDCODE danh sách User (thay thế cho việc gọi Service/Database)
		// List<User> userList = Arrays.asList(
		// new User("Nguyễn Văn A", "a.nguyen@example.com", "Hà Nội"),
		// new User("Trần Thị B", "b.tran@example.com", "TP.HCM"),
		// new User("Lê Văn C", "c.le@example.com", "Đà Nẵng"));
		// model.addAttribute("users", userList);

		// cách làm sai 1
		// UserServiceWrong1 usw1 = new UserServiceWrong1();
		// List<User> userServiceWrong1 = usw1.fetchUsers();
		// model.addAttribute("users", userServiceWrong1);

		// cách làm sai 2 (static)
		// List<User> userServiceWrong2 = UserServiceWrong2.fetchUsers();
		// model.addAttribute("users", userServiceWrong2);

		// cách làm Dependency Injection
		List<User> userList = this._userService.fetchUsers();
		model.addAttribute("users", userList);

		return "/user/show";
	}

	@GetMapping("/user/create")
	public String getCreatePage(Model model) {
		model.addAttribute("user", new User());
		return "/user/create";
	}

	@PostMapping("/user/create")
	public String getPostPage(@Valid @ModelAttribute User createUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/user/create";
		}

		this._userService.createUser(createUser);
		return "redirect:/user";
	}

	@GetMapping("/user/{id}")
	public String getUpdateUserPage(Model model, @PathVariable int id) {
		Optional<User> userOptional = this._userService.fetchUserById(id);

		if (userOptional.isPresent()) {
			model.addAttribute("user", userOptional.get());
			model.addAttribute("id", id);

			return "/user/update";
		} else {
			return "redirect:/user";
		}
	}

	@PostMapping("/user/update")
	public String postUpdatePage(@Valid @ModelAttribute User updateUser, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			System.out.println("ERROR!!!!!!");

			bindingResult.getAllErrors().forEach(error -> System.out.println(error.toString()));

			model.addAttribute("user", updateUser);
			model.addAttribute("id", updateUser.getId());

			return "/user/update";
		}

		this._userService.updateUser(updateUser);
		return "redirect:/user";
	}

	@PostMapping("/user/delete/{id}")
	public String postDeleteUser(@PathVariable int id) {
		this._userService.deleteUser(id);
		return "redirect:/user";
	}
}
