package com.ak0411.filmfolio.domain;

import com.ak0411.filmfolio.enums.Rating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Column(nullable = false)
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    @JsonIgnore
    private Film film;

    public Review(String text, Rating rating, User user, Film film) {
        this.text = text;
        this.rating = rating;
        this.user = user;
        this.film = film;
    }
}

