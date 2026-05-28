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
import vn.hoidanit.springsieutoc.service.UserServiceWrong1;
import vn.hoidanit.springsieutoc.service.UserServiceWrong2;

@Controller // MVC
public class HelloController {

	private final UserService _userService;

	public HelloController(UserService userService) {
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
	public String getPostPage(@ModelAttribute User createUser, Model model) {
		createUser.setId(1);

		List<User> userList = Arrays.asList(createUser);
		model.addAttribute("users", userList);

		return "/user/show";
	}

	@GetMapping("/user/{id}")
	public String getUpdateUserPage(Model model, @PathVariable int id) {
		List<User> userList = this._userService.fetchUsers();

		User updateUser = userList.stream().filter(user -> user.getId() == id)
				.findFirst().get();

		System.out.println(updateUser);

		model.addAttribute("user", updateUser);
		model.addAttribute("id", id);

		return "/user/update";
	}

	@PostMapping("/user/update")
	public String postUpdatePage(@ModelAttribute User updateUser, Model model) {
		updateUser.setId(1);

		List<User> userList = Arrays.asList(updateUser);
		model.addAttribute("users", userList);

		return "/user/show";
	}

	@PostMapping("/user/delete/{id}")
	public String postDeleteUser(Model model, @PathVariable int id) {
		List<User> userList = this._userService.fetchUsers();
		List<User> newUserList = userList.stream().filter(user -> user.getId() != id)
				.collect(Collectors.toList());
		model.addAttribute("users", newUserList);

		return "/user/show"; // hoặc redirect:/user
	}
}
