package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.enums.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpDto(
        String name,

        @NotNull(message = "Username cannot be empty")
        @Size(min = 3, max = 20, message = "Username should be between 3 - 20 characters long")
        String username,

        @NotNull(message = "Password cannot be empty")
        @Size(min = 5, message = "Password should be at least 5 characters long")
        String password,

        @Enumerated
        UserRole role
) {
}
