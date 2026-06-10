package com.practice.__spring_security_practice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.__spring_security_practice.models.User;
import com.practice.__spring_security_practice.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return this._userRepository.findAll();
    }

    public void createUser(User user) {
        if (user == null)
            return;

        this._userRepository.save(user);
    }

    public void updateUser(User user) {
        if (user == null)
            return;

        this._userRepository.save(user);
    }

    public void deleteUser(int id) {
        if (id <= 0)
            return;

        this._userRepository.deleteById(id);
    }
}
