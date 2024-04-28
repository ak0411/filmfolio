package com.ak0411.dtos;

import com.ak0411.enums.UserRole;

public record SignUpDto(
        String username,
        String password,
        UserRole role
) {
}
