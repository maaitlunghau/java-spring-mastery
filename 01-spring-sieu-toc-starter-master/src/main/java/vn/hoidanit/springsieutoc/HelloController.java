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

package vn.hoidanit.springsieutoc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
	@GetMapping("/hoidanit")
	public String index() { // RestController
		return "Hello World from Spring Boot - @hoidanit devtools";
	}

	@GetMapping("/dev") // RestController
	public String maaitlunghau() {
		return "Hello maaitlunghau";
	}

	@GetMapping("/template-engine") // Controller (MVC)
	public String testTemplateEngine() {
		return "hello.html";
	}
}
