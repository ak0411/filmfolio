package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.enums.Rating;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(Views.UserExtended.class)
    private Long id;

    @JsonView({Views.User.class, Views.Film.class})
    private String text;

    @Column(nullable = false)
    @JsonView({Views.User.class, Views.Film.class})
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.FilmExtended.class)
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    @JsonView(Views.User.class)
    private Film film;

    public Review(String text, Rating rating, User user, Film film) {
        this.text = text;
        this.rating = rating;
        this.user = user;
        this.film = film;
    }
}

