package com.ak0411.filmfolio.services.impl;

import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.exceptions.UserNotFoundException;
import com.ak0411.filmfolio.repositories.UserRepository;
import com.ak0411.filmfolio.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> readAll() {
        return userRepository.findAll();
    }

    public User readOne(UUID id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
