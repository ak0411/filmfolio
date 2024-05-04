package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.dtos.ReviewDto;
import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.Review;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.AlreadyReviewedException;
import com.ak0411.filmfolio.exceptions.FilmNotFoundException;
import com.ak0411.filmfolio.repositories.FilmRepository;
import com.ak0411.filmfolio.repositories.ReviewRepository;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    public FilmService(FilmRepository filmRepository, UserRepository userRepository, ReviewRepository reviewRepository) {
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Film> readAll() {
        return filmRepository.findAll();
    }

    public Film readOne(Long filmId) {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));
    }

    public Film create(Film film) {
        return filmRepository.save(film);
    }

    public void favorite(Long filmId) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));

        currentUser.addFavorite(film);

        userRepository.save(currentUser);
    }

    public void unfavorite(Long filmId) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));

        currentUser.removeFavorite(film);

        userRepository.save(currentUser);
    }

    public void createReview(Long filmId, ReviewDto reviewDto) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));

        if (reviewRepository.existsByFilmAndUser(film, currentUser)) {
            throw new AlreadyReviewedException();
        }

        Review review = new Review(reviewDto.text(), reviewDto.rating(), currentUser, film);

        reviewRepository.save(review);
    }

    public Film update(Long filmId, Film filmToUpdate) {
        return filmRepository.findById(filmId)
                .map(film -> {
                    film.setTitle(filmToUpdate.getTitle());
                    film.setYear(filmToUpdate.getYear());
                    film.setGenre(filmToUpdate.getGenre());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    filmToUpdate.setId(filmId);
                    return filmRepository.save(filmToUpdate);
                });
    }

    public void remove(Long filmId) {
        filmRepository.deleteById(filmId);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
