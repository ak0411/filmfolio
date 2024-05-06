package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.dtos.ReviewDto;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.services.FilmService;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(Views.Film.class)
    List<Film> readAll() {
        return filmService.readAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FilmExtended.class)
    Film readOne(@PathVariable String id) {
        return filmService.readOne(id);
    }

    @PostMapping
    Film create(@RequestBody Film request) {
        return filmService.create(request);
    }

    @PostMapping("/{id}/favorite")
    void favorite(@PathVariable String id) {
        filmService.favorite(id);
    }

    @PostMapping("/{id}/unfavorite")
    void unfavorite(@PathVariable String id) {
        filmService.unfavorite(id);
    }

    @PostMapping("/{id}/review")
    void createReview(
            @PathVariable String id,
            @RequestBody ReviewDto request
    ) {
        filmService.createReview(id, request);
    }

    @PutMapping("/{id}")
    Film update(@PathVariable String id, @RequestBody Film request) {
        return filmService.update(id, request);
    }

    @DeleteMapping("/{id}")
    void remove(@PathVariable String id) {
        filmService.remove(id);
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
