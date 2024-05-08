package com.ak0411.filmfolio.domain.entities;

import com.ak0411.filmfolio.enums.Rating;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
