package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.dtos.ReviewDto;
import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.Review;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.FilmNotFoundException;
import com.ak0411.filmfolio.exceptions.UserNotFoundException;
import com.ak0411.filmfolio.repositories.FilmRepository;
import com.ak0411.filmfolio.repositories.ReviewRepository;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    FilmController(FilmRepository repository,
                   UserRepository userRepository,
                   ReviewRepository reviewRepository
    ) {
        this.filmRepository = repository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    List<Film> readAll() {
        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    Film readOne(@PathVariable Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));
    }

    @PostMapping
    Film create(@RequestBody Film request) {
        return filmRepository.save(request);
    }

    @PostMapping("/{id}/favorite")
    void favorite(@PathVariable Long id, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        currentUser.addFavorite(film);

        userRepository.save(currentUser);
    }

    @PostMapping("/{id}/unfavorite")
    ResponseEntity<?> unfavorite(@PathVariable Long id, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        currentUser.removeFavorite(film);

        userRepository.save(currentUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{filmId}/review")
    ResponseEntity<?> createReview(
            @PathVariable Long filmId,
            @RequestBody ReviewDto request,
            Authentication authentication
    ) {
        User currentUser = (User) authentication.getPrincipal();

        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException(filmId));

        if (reviewRepository.existsByFilmAndUser(film, currentUser)) {
            return ResponseEntity.badRequest().body("You have already reviewed this film.");
        }

        Review review = new Review(request.text(), request.rating(), currentUser, film);
        reviewRepository.save(review);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    Film update(@PathVariable Long id, @RequestBody Film request) {

        return filmRepository.findById(id)
                .map(film -> {
                    film.setTitle(request.getTitle());
                    film.setYear(request.getYear());
                    film.setGenre(request.getGenre());
                    return filmRepository.save(film);
                })
                .orElseGet(() -> {
                    request.setId(id);
                    return filmRepository.save(request);
                });
    }

    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }


    private User getCurrentUser(Authentication authentication) {
        User currentPrincipal = (User) authentication.getPrincipal();
        return userRepository.findById(currentPrincipal.getId())
                .orElseThrow(() -> new UserNotFoundException(currentPrincipal.getId()));
    }
}
