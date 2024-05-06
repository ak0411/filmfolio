package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.dtos.SignUpDto;
import com.ak0411.filmfolio.exceptions.InvalidJwtException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    UserDetails signUp(SignUpDto data) throws InvalidJwtException;
}
