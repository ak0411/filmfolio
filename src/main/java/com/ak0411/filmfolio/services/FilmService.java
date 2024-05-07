package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.dtos.ReviewDto;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;

import java.util.List;

public interface FilmService {

    List<Film> readAll();

    Film readOne(String id);

    Film create(Film film);

    void favorite(String id);

    void unfavorite(String id);

    Review createReview(String id, ReviewDto reviewDto);

    Film update(String id, Film updatedFilm);

    void remove(String imdbId);
}
