package com.ak0411.filmfolio.services.impl;

import com.ak0411.filmfolio.domain.dtos.SignUpDto;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.exceptions.InvalidJwtException;
import com.ak0411.filmfolio.repositories.UserRepository;
import com.ak0411.filmfolio.services.AuthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails signUp(SignUpDto data) throws InvalidJwtException {
        if (userRepository.findByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.username(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }
}
