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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.springsieutoc.model.User;
import vn.hoidanit.springsieutoc.service.UserService;

@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String getUserPage(Model model) {
		List<User> users = this.userService.fetchUsers();
		model.addAttribute("users", users);
		return "/user/show";
	}

	@GetMapping("/user/create")
	public String getCreateUserPage(Model model) {
		model.addAttribute("user", new User());
		return "/user/create";
	}

	@PostMapping("/user/create")
	public String postCreateUser(@ModelAttribute User user, Model model) {
		user.setId(99);
		List<User> userList = Arrays.asList(user);
		model.addAttribute("users", userList); // x <= y
		return "/user/show";
	}

	@GetMapping("/user/{id}")
	public String getUpdateUserPage(Model model, @PathVariable int id) {
		List<User> userList = this.userService.fetchUsers();
		User updateUser = userList.stream().filter(user -> user.getId() == id).findFirst().get();
		model.addAttribute("user", updateUser);
		return "/user/update";
	}

	@PostMapping("/user/update")
	public String postUpdatePage(@ModelAttribute User updateUser, Model model) {
		updateUser.setId(99);
		List<User> userList = Arrays.asList(updateUser);
		model.addAttribute("users", userList); // x <= y
		return "/user/show";
	}

	@PostMapping("/user/delete/{id}")
	public String postDeleteUser(Model model, @PathVariable int id) {
		List<User> userList = this.userService.fetchUsers().stream().filter(user -> user.getId() != id)
				.collect(Collectors.toList());
		model.addAttribute("users", userList);
		return "/user/show";
	}
}
