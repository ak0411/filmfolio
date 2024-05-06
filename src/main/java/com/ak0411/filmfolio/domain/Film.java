package com.ak0411.filmfolio.domain;

import com.ak0411.filmfolio.enums.Genre;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
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
    @JsonProperty("imdb_id")
    @JsonView({Views.UserExtended.class, Views.Film.class})
    private String id;

    @Column(nullable = false, length = 50)
    @JsonView({Views.User.class, Views.Film.class})
    private String title;

    @Column(nullable = false)
    @JsonView({Views.UserExtended.class, Views.FilmExtended.class})
    private Integer year;

    @JsonView({Views.UserExtended.class, Views.FilmExtended.class})
    private Set<Genre> genre;

    @JsonProperty("favorites")
    @JsonView(Views.Film.class)
    private int numberOfFavorites;

    @ManyToMany(mappedBy = "favoriteFilms")
    private Set<User> favoritedByUsers;

    @OneToMany(mappedBy = "film")
    @JsonView(Views.Film.class)
    private List<Review> reviews;

    public Film(String id, String title, Integer year, Set<Genre> genre) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public Film(String title, Integer year, Set<Genre> genre) {
        this(null, title, year, genre);
    }

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }
}
