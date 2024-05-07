package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.enums.UserRole;

public record SignUpDto(
        String name,
        String username,
        String password,
        UserRole role
) {
}