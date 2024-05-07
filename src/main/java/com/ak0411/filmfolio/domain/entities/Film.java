package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.enums.Genre;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer year;

    private Set<Genre> genre;

    private int numberOfFavorites;

    @ManyToMany(mappedBy = "favoriteFilms")
    private Set<User> favoritedByUsers;

    @OneToMany(mappedBy = "film")
    private List<Review> reviews;

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }
}
