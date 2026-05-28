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

package vn.hoidanit.springsieutoc.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.User;

@Service
public class UserService {

    public List<User> fetchUsers() {
        List<User> userList = Arrays.asList(
                new User(1, "Hỏi Dân IT vs Eric", "hoidanit@example.com", "Hà Nội"),
                new User(2, "Nguyễn Văn A", "a.nguyen@example.com", "Hà Nội"),
                new User(3, "Trần Thị B", "b.tran@example.com", "TP.HCM"),
                new User(4, "Lê Văn C", "c.le@example.com", "Đà Nẵng"));

        return userList;
    }

}
