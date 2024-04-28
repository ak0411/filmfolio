package com.ak0411.controllers;

import com.ak0411.entities.User;
import com.ak0411.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping
    List<User> getAll() { return userRepository.findAll(); }
}
