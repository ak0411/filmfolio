package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.dtos.ReviewDto;
import com.ak0411.filmfolio.entities.Film;
import com.ak0411.filmfolio.entities.User;
import com.ak0411.filmfolio.services.FilmService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {

    private final FilmService filmService;

    FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    List<Film> readAll() {
        return filmService.readAll();
    }

    @GetMapping("/{id}")
    Film readOne(@PathVariable Long id) {
        return filmService.readOne(id);
    }

    @PostMapping
    Film create(@RequestBody Film request) {
        return filmService.create(request);
    }

    @PostMapping("/{id}/favorite")
    void favorite(@PathVariable Long id) {
        filmService.favorite(id);
    }

    @PostMapping("/{id}/unfavorite")
    void unfavorite(@PathVariable Long id) {
        filmService.unfavorite(id);
    }

    @PostMapping("/{filmId}/review")
    void createReview(
            @PathVariable Long filmId,
            @RequestBody ReviewDto request
    ) {
        filmService.createReview(filmId, request);
    }

    @PutMapping("/{id}")
    Film update(@PathVariable Long id, @RequestBody Film request) {
        return filmService.update(id, request);
    }

    @DeleteMapping("/{id}")
    void remove(@PathVariable Long id) {
        filmService.remove(id);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
