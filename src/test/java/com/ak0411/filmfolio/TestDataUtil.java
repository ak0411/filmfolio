package com.ak0411.filmfolio;

import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.enums.Genre;
import com.ak0411.filmfolio.enums.UserRole;

import java.util.Set;

public class TestDataUtil {
    private TestDataUtil() {
    }

    public static Film createTestFilm() {
        return Film.builder()
                .id(1L)
                .title("Spiderman 1")
                .year(2002)
                .genre(Set.of(Genre.ACTION))
                .build();
    }

    public static User createTestUser() {
        return User.builder()
                .id(1L)
                .username("bob")
                .password("bob123")
                .role(UserRole.USER)
                .build();
    }

    public static User createTestAdmin() {
        return User.builder()
                .id(0L)
                .username("admin")
                .password("password")
                .role(UserRole.ADMIN)
                .build();
    }
}
