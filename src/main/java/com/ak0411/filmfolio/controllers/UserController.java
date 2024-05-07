package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.dtos.UserDto;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.mappers.Mapper;
import com.ak0411.filmfolio.services.UserService;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
    private final Mapper<User, UserDto> userMapper;

    UserController(UserService userService, Mapper<User, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @JsonView(Views.User.class)
    List<UserDto> readAll() {
        List<User> users = userService.readAll();
        return userMapper.mapAll(users);
    }

    @GetMapping("/{id}")
    @JsonView(Views.UserExtended.class)
    UserDto readOne(@PathVariable UUID id) {
        User user = userService.readOne(id);
        return userMapper.mapTo(user);
    }
}
