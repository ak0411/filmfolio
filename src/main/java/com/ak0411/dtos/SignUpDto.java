package com.ak0411.dtos;

import com.ak0411.enums.UserRole;

public record SignUpDto(
        String login,
        String password,
        UserRole role
) {
}
