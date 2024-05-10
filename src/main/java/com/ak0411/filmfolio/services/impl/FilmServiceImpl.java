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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public FilmServiceImpl(FilmRepository filmRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Film> readAll() {
        return StreamSupport
                .stream(filmRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Page<Film> readAll(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    public Film readOne(String id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    public Film create(Film film) {
        String filmId = film.getId();
        if (filmRepository.existsById(filmId)) {
            throw new FilmAlreadyExistsException(filmId);
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
        return filmRepository.findById(updateFilm.getId())
                .map(film -> {
                    film.setTitle(updateFilm.getTitle());
                    film.setYear(updateFilm.getYear());
                    film.setGenre(updateFilm.getGenre());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    updateFilm.setId(updateFilm.getId());
                    return filmRepository.save(updateFilm);
                });
    }

    public void deleteFilm(String id) {
        filmRepository.deleteById(id);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
