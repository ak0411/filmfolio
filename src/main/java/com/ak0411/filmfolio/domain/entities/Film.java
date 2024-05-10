package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Pattern(regexp = "tt\\d+", message = "Film id should follow the imdbId format")
    @JsonProperty("imdb_id")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @YearValidator
    private Integer year;

    @Enumerated
    private Set<Genre> genre;

    @JsonProperty("favorites")
    private int numberOfFavorites;

    @ManyToMany(mappedBy = "favoriteFilms")
    @JsonIgnore
    private List<User> favoritedByUsers;

    @OneToMany(mappedBy = "film")
    @JsonIncludeProperties({"text", "rating"})
    private List<Review> reviews;

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }
}
