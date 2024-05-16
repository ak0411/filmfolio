package com.ak0411.filmfolio;

import com.ak0411.filmfolio.domain.dtos.SignUpDto;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.enums.UserRole;

import java.util.UUID;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static User createTestUserAlice() {
        return User.builder()
                .id(UUID.fromString("695480db-4b07-4e57-b16f-87041aebd57c"))
                .name("Alice Wonder")
                .username("alice")
                .password("alice123")
                .role(UserRole.ADMIN)
                .build();
    }

    public static User createTestUserBob() {
        return User.builder()
                .name("Bob Builder")
                .username("bob")
                .password("bob123")
                .role(UserRole.USER)
                .build();
    }

    public static User createTestUserTrudy() {
        return User.builder()
                .id(UUID.fromString("2120db9c-3e0d-4226-8b4e-675947dd4b93"))
                .name("Trudy Intruder")
                .username("trudy")
                .password("trudy123")
                .role(UserRole.USER)
                .build();
    }

    public static SignUpDto signUpTestUserBob() {
        return new SignUpDto("Bob Builder", "bob", "bob123", UserRole.USER);
    }
}
