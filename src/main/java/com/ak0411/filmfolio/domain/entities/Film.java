package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.annotations.GenreValidator;
import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
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
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @YearValidator
    private Integer year;

    @GenreValidator(enumClass = Genre.class)
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
