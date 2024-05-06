package com.ak0411.filmfolio.domain;

import com.ak0411.filmfolio.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Integer year;

    private Set<Genre> genre;

    @JsonProperty("favorites")
    private int numberOfFavorites;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteFilms")
    private Set<User> favoritedByUsers;

    @OneToMany(mappedBy = "film")
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