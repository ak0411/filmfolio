package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.Film;
import com.ak0411.filmfolio.domain.Review;
import com.ak0411.filmfolio.domain.User;
import com.ak0411.filmfolio.dtos.ReviewDto;
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

    public Film readOne(String id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    public Film create(Film film) {
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

    public void createReview(String id, ReviewDto reviewDto) {
        User currentUser = getCurrentUser();

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        if (reviewRepository.existsByFilmAndUser(film, currentUser)) {
            throw new AlreadyReviewedException();
        }

        Review review = new Review(reviewDto.text(), reviewDto.rating(), currentUser, film);

        reviewRepository.save(review);
    }

    public Film update(String id, Film updatedFilm) {
        return filmRepository.findById(id)
                .map(film -> {
                    film.setTitle(updatedFilm.getTitle());
                    film.setYear(updatedFilm.getYear());
                    film.setGenre(updatedFilm.getGenre());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    updatedFilm.setId(id);
                    return filmRepository.save(updatedFilm);
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
