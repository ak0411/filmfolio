package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.dtos.SignUpDto;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.InvalidJwtException;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails signUp(SignUpDto data) throws InvalidJwtException {
        if (userRepository.findByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.username(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }
}
