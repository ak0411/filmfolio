package com.ak0411.filmfolio.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "films")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = "imdbId")
public class Film {
    @Id
    @Column(name = "imdb_id")
    @JsonProperty("imdb_id")
    private String imdbId;

    @Column(nullable = false)
    private String title;

    @Column(name = "release_date")
    @JsonProperty("release_date")
    private String releaseDate;

    private List<String> genres;

    @Column(length = 1000)
    private String overview;

    @Column(name = "poster_path")
    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("favorites")
    private int numberOfFavorites;

    @ManyToMany(mappedBy = "favoriteFilms")
    @JsonIgnore
    private List<User> favoritedByUsers;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @JsonIncludeProperties({"text", "rating"})
    private List<Review> reviews;

    public int getNumberOfFavorites() {
        return favoritedByUsers != null ? favoritedByUsers.size() : 0;
    }

    @PreRemove
    private void preRemove() {
        for (User user : favoritedByUsers) {
            user.removeFavorite(this);
        }
    }
}
