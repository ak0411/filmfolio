package com.ak0411.filmfolio.services.impl;

import com.ak0411.filmfolio.domain.dtos.ReviewDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return filmRepository.findAll();
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

    public Review createReview(String id, ReviewDto reviewDto) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        if (reviewRepository.existsByFilmAndUser(film, currentUser)) {
            throw new AlreadyReviewedException();
        }

        Review review = Review.builder()
                .text(reviewDto.getText())
                .rating(reviewDto.getRating())
                .user(currentUser)
                .film(film)
                .build();

        return reviewRepository.save(review);
    }

    /* TODO: add an appropriate error message when id is malformed instead of
        "500 Internal Server Error: Could not commit JPA transaction" */
    public Film update(Film filmToUpdate) {
        return filmRepository.findById(filmToUpdate.getId())
                .map(film -> {
                    film.setTitle(filmToUpdate.getTitle());
                    film.setYear(filmToUpdate.getYear());
                    film.setGenre(filmToUpdate.getGenre());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    filmToUpdate.setId(filmToUpdate.getId());
                    return filmRepository.save(filmToUpdate);
                });
    }

    public void remove(String imdbId) {
        filmRepository.deleteById(imdbId);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
