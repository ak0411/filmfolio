package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> readAll();

    User readOne(UUID id);
}
