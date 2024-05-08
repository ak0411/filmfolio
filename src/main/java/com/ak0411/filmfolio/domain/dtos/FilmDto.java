package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.domain.entities.Review;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.enums.Genre;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilmDto {

    @JsonProperty("imdb_id")
    @JsonView({Views.UserExtended.class, Views.Film.class})
    private String id;

    @JsonView({Views.User.class, Views.Film.class})
    private String title;

    @JsonView({Views.UserExtended.class, Views.Film.class})
    private Integer year;

    @JsonView({Views.UserExtended.class, Views.Film.class})
    private Set<Genre> genre;

    @JsonProperty("favorites")
    @JsonView(Views.Film.class)
    private int numberOfFavorites;

    private Set<User> favoritedByUsers;

    @JsonView(Views.Film.class)
    private List<Review> reviews;
}
