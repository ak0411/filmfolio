package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.services.UserService;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<User>> readAll() {
        List<User> response = userService.readAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> readOne(@PathVariable UUID id) {
        User response = userService.readOne(id);
        return ResponseEntity.ok(response);
    }
}
