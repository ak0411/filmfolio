package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.User;
import com.ak0411.filmfolio.repositories.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> readAll() {
        return userService.readAll();
    }

    @GetMapping("/{id}")
    User readOne(@PathVariable UUID id) {
        return userService.readOne(id);
    }
}
