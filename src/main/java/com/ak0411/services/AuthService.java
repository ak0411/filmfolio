package com.ak0411.services;

import com.ak0411.dtos.SignUpDto;
import com.ak0411.exceptions.InvalidJwtException;
import com.ak0411.entities.User;
import com.ak0411.repositories.UserRepository;
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
        return userRepository.findByLogin(username);
    }

    public UserDetails signUp(SignUpDto data) throws InvalidJwtException {
        if (userRepository.findByLogin(data.login()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }
}
