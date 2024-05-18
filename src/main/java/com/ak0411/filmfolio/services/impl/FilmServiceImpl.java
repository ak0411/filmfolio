package com.ak0411.filmfolio.services.impl;

import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.exceptions.AlreadyReviewedException;
import com.ak0411.filmfolio.exceptions.FilmAlreadyExistsException;
import com.ak0411.filmfolio.exceptions.FilmNotFoundException;
import com.ak0411.filmfolio.repositories.FilmRepository;
import com.ak0411.filmfolio.repositories.ReviewRepository;
import com.ak0411.filmfolio.repositories.UserRepository;
import com.ak0411.filmfolio.services.FilmService;
import com.ak0411.filmfolio.services.TmdbService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final TmdbService tmdbService;

    public FilmServiceImpl(FilmRepository filmRepository, UserRepository userRepository, ReviewRepository reviewRepository, TmdbService tmdbService) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.tmdbService = tmdbService;
    }

    public List<Film> readAll() {
        return new ArrayList<>(filmRepository.findAll());
    }

    public Page<Film> readAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    public Film readOne(String id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    public Film add(String imdbId) {
        if (filmRepository.existsById(imdbId)) {
            throw new FilmAlreadyExistsException(imdbId);
        }

        Film film = tmdbService.fetchFilmByImdbId(imdbId).block();
        if (film != null) {
            if (filmRepository.existsById(imdbId)) {
                throw new FilmAlreadyExistsException(imdbId);
            }
            return filmRepository.save(film);
        } else {
            throw new RuntimeException("Film not found for IMDb ID: " + imdbId);
        }
    }

    public Film create(Film film) {
        String id = film.getImdbId();
        if (filmRepository.existsById(id)) {
            throw new FilmAlreadyExistsException(id);
        }
        return filmRepository.save(film);
    }

    public void favorite(String id) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        currentUser.addFavorite(film);

        userRepository.save(currentUser);
    }

    public void unfavorite(String id) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        currentUser.removeFavorite(film);

        userRepository.save(currentUser);
    }

    public Review createReview(String id, Review newReview) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        if (reviewRepository.existsByFilmAndUser(film, currentUser)) {
            throw new AlreadyReviewedException();
        }

        Review review = Review.builder()
                .text(newReview.getText())
                .rating(newReview.getRating())
                .user(currentUser)
                .film(film)
                .build();

        return reviewRepository.save(review);
    }

    public Film update(Film updateFilm) {
        return filmRepository.findById(updateFilm.getImdbId())
                .map(film -> {
                    film.setTitle(updateFilm.getTitle());
                    film.setReleaseDate(updateFilm.getReleaseDate());
                    film.setGenres(updateFilm.getGenres());
                    film.setOverview(updateFilm.getOverview());
                    film.setPosterPath(updateFilm.getPosterPath());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    updateFilm.setImdbId(updateFilm.getImdbId());
                    return filmRepository.save(updateFilm);
                });
    }

    @Transactional
    public void deleteFilm(String id) {
        filmRepository.deleteById(id);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
