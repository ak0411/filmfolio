package com.ak0411.filmfolio.domain.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignInDto(
        @NotNull(message = "Username cannot be empty")
        @Size(min = 3, max = 20, message = "Username should be between 3 - 20 characters long")
        String username,

        @NotNull(message = "Password cannot be empty")
        @Min(value = 5, message = "Password should be at least 5 characters long")
        String password
) {
}
