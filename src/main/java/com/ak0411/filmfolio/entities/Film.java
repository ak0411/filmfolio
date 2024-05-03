package com.ak0411.filmfolio.entities;

import com.ak0411.filmfolio.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer year;
    private Set<Genre> genre;

    private int numberOfFavorites;

    @JsonIgnore
    @ManyToMany(mappedBy = "favoriteFilms")
    private Set<User> favoritedByUsers;

    public Film(String title, Integer year, Set<Genre> genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }
}
