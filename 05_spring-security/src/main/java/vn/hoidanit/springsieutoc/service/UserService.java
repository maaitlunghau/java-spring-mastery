package vn.hoidanit.springsieutoc.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.User;
import vn.hoidanit.springsieutoc.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchUsers() {
        List<User> userList = this.userRepository.findAll();
        return userList;
    }

    public Optional<User> fetchUserById(int id) {
        return this.userRepository.findById(id);
    }

    public void createUser(User user) {
        this.userRepository.save(user);
    }

    public void updateUser(User user) {
        if (this.userRepository.existsById(user.getId())) {
            this.userRepository.save(user);
        } else {
            System.out.println("User with ID " + user.getId() + " not found!");
        }
    }

    public void deleteUser(int id) {
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        } else {
            System.out.println("User with ID " + id + " does not exist. Cannot delete!");
        }
    }

    public void testJPA() {
        System.out.println("Call JPA");

        Optional<User> userOpt = this.userRepository.findByName("maaitlunghau");
        Optional<User> user2Opt = this.userRepository.findByNameAndEmail(
                "maaitlunghau",
                "chunhau.py@gmail.com"
       );


        System.out.println(userOpt.get());
        System.out.println(user2Opt.get());
    }
}
