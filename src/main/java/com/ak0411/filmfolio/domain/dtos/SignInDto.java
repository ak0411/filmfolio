package com.ak0411.filmfolio.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record SignInDto(
        @NotNull(message = "Username cannot be empty")
        String username,

        @NotNull(message = "Password cannot be empty")
        String password
) {
}
