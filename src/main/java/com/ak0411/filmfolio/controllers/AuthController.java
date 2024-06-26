package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.config.auth.TokenProvider;
import com.ak0411.filmfolio.domain.dtos.UserTokenDto;
import com.ak0411.filmfolio.domain.dtos.SignInDto;
import com.ak0411.filmfolio.domain.dtos.SignUpDto;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenProvider tokenService;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, TokenProvider tokenService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto data) {
        authService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<UserTokenDto> signIn(@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var currentUser = (User) authUser.getPrincipal();
        var accessToken = tokenService.generateAccessToken(currentUser);
        return ResponseEntity.ok(new UserTokenDto(currentUser.getName(), currentUser.getUsername(), accessToken));
    }
}
