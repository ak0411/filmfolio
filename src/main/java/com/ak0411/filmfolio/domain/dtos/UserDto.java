package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;
import com.ak0411.filmfolio.enums.UserRole;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @JsonView(Views.User.class)
    private UUID id;

    @JsonView(Views.User.class)
    private String name;

    @JsonView(Views.User.class)
    private String username;

    private String password;

    private UserRole role;

    @JsonView(Views.User.class)
    private Set<Film> favoriteFilms;

    @JsonView(Views.User.class)
    private List<Review> reviews;
}
