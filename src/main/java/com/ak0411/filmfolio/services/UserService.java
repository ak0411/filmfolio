package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.UserNotFoundException;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
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
