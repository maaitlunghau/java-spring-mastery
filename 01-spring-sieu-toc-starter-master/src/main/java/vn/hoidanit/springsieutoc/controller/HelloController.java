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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.springsieutoc.model.User;

@Controller // MVC
public class HelloController {

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
		List<User> userList = Arrays.asList(
				new User("Nguyễn Văn A", "a.nguyen@example.com", "Hà Nội"),
				new User("Trần Thị B", "b.tran@example.com", "TP.HCM"),
				new User("Lê Văn C", "c.le@example.com", "Đà Nẵng"));

		model.addAttribute("users", userList);

		return "/user/showUser";
	}
}
