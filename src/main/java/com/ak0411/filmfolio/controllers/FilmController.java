package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.exceptions.FilmNotFoundException;
import com.ak0411.filmfolio.exceptions.UserNotFoundException;
import com.ak0411.filmfolio.repositories.FilmRepository;
import com.ak0411.filmfolio.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {

    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    FilmController(FilmRepository repository, UserRepository userRepository) {
        this.filmRepository = repository;
        this.userRepository = userRepository;
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

        currentUser.getFavoriteFilms().add(film);

        userRepository.save(currentUser);
    }

    @PostMapping("/{id}/unfavorite")
    void unfavorite(@PathVariable Long id, Authentication authentication) {
        User currentUser = getCurrentUser(authentication);

        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException(id));

        currentUser.getFavoriteFilms().remove(film);

        userRepository.save(currentUser);
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
