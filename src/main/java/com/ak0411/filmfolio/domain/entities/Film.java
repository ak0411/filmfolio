package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.annotations.EnumValidator;
import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Film {

    @Id
    @Pattern(regexp = "tt\\d{7}", message = "Film id should follow the imdbId format")
    @JsonProperty("imdb_id")
    @JsonView({Views.UserExtended.class, Views.Film.class})
    private String id;

    @Column(nullable = false)
    @JsonView({Views.User.class, Views.Film.class})
    private String title;

    @Column(nullable = false)
    @YearValidator
    @JsonView({Views.UserExtended.class, Views.Film.class})
    private Integer year;

    @EnumValidator(enumClass = Genre.class)
    @JsonView({Views.UserExtended.class, Views.Film.class})
    private Set<Genre> genre;

    @JsonProperty("favorites")
    @JsonView(Views.Film.class)
    private int numberOfFavorites;

    @ManyToMany(mappedBy = "favoriteFilms")
    private Set<User> favoritedByUsers;

    @OneToMany(mappedBy = "film")
    @JsonView(Views.Film.class)
    private List<Review> reviews;

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }
}
