package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {

    List<Film> readAll();

    Page<Film> readAll(Pageable pageable);

    Film readOne(String id);

    Film create(Film film);

    void favorite(String id);

    void unfavorite(String id);

    Review createReview(String id, Review newReview);

    Film update(Film updatedFilm);

    void deleteFilm(String imdbId);
}
