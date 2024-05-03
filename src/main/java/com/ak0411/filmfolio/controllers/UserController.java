package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.UserNotFoundException;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
class UserController {

    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    User readOne(@PathVariable UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
