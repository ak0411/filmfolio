package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.dtos.FilmDto;
import com.ak0411.filmfolio.domain.dtos.NewFilmDto;
import com.ak0411.filmfolio.domain.dtos.ReviewDto;
import com.ak0411.filmfolio.domain.dtos.UpdateFilmDto;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.mappers.Mapper;
import com.ak0411.filmfolio.services.FilmService;
import com.ak0411.filmfolio.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {

    private final FilmService filmService;
    private final Mapper<Film, FilmDto> filmMapper;

    FilmController(FilmService filmService, Mapper<Film, FilmDto> filmMapper) {
        this.filmService = filmService;
        this.filmMapper = filmMapper;
    }

    @GetMapping
    @JsonView(Views.Film.class)
    List<FilmDto> readAll() {
        List<Film> films = filmService.readAll();
        return filmMapper.mapAll(films);
    }

    @GetMapping("/{id}")
    @JsonView(Views.FilmExtended.class)
    FilmDto readOne(@PathVariable String id) {
        Film film = filmService.readOne(id);
        return filmMapper.mapTo(film);
    }


    @PostMapping
    ResponseEntity<FilmDto> create(@Valid @RequestBody NewFilmDto request) {

        Film film = Film.builder()
                .id(request.id())
                .title(request.title())
                .year(request.year())
                .genre(request.genre())
                .build();
        filmService.create(film);

        FilmDto filmDto = filmMapper.mapTo(film);
        return new ResponseEntity<FilmDto>(filmDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/favorite")
    ResponseEntity<String> favorite(@PathVariable String id) {
        filmService.favorite(id);
        return new ResponseEntity<>("film favorited!", HttpStatus.OK);
    }

    @PostMapping("/{id}/unfavorite")
    ResponseEntity<String> unfavorite(@PathVariable String id) {
        filmService.unfavorite(id);
        return new ResponseEntity<>("film unfavorited", HttpStatus.OK);
    }

    @PostMapping("/{id}/review")
    ResponseEntity<ReviewDto> createReview(@PathVariable String id, @RequestBody ReviewDto request) {
        Review review = filmService.createReview(id, request);
        ReviewDto response = ReviewDto.builder()
                .id(review.getId())
                .text(review.getText())
                .rating(review.getRating())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<FilmDto> update(@PathVariable String id, @Valid @RequestBody UpdateFilmDto request) {
        Film filmToUpdate = Film.builder()
                .id(id)
                .title(request.title())
                .year(request.year())
                .genre(request.genre())
                .build();

        Film film = filmService.update(filmToUpdate);
        FilmDto response = filmMapper.mapTo(film);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
