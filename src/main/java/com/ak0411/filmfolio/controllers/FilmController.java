package com.ak0411.filmfolio.controllers;

import com.ak0411.filmfolio.domain.dtos.FilmCreateDto;
import com.ak0411.filmfolio.domain.dtos.FilmUpdateDto;
import com.ak0411.filmfolio.domain.dtos.ReviewCreateDto;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.domain.entities.Review;
import com.ak0411.filmfolio.domain.entities.User;
import com.ak0411.filmfolio.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {
    private final FilmService filmService;

    FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    Page<Film> readAll(Pageable pageable) {
        return filmService.readAll(pageable);
    }

    @GetMapping("/{id}")
    ResponseEntity<Film> readOne(@PathVariable String id) {
        Film response = filmService.readOne(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{imdbId}")
    public ResponseEntity<Film> addFilm(@PathVariable String imdbId) {
        Film film = filmService.add(imdbId);
        return ResponseEntity.status(HttpStatus.CREATED).body(film);
    }

    @PostMapping
    ResponseEntity<Film> createFilm(@Valid @RequestBody FilmCreateDto dto) {
        Film film = Film.builder()
                .imdbId(dto.imdbId())
                .title(dto.title())
                .releaseDate(dto.releaseDate())
                .genres(dto.genres())
                .overview(dto.overview())
                .posterPath(dto.posterPath())
                .build();

        Film response = filmService.create(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/favorite")
    ResponseEntity<?> favorite(@PathVariable String id) {
        filmService.favorite(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/unfavorite")
    ResponseEntity<?> unfavorite(@PathVariable String id) {
        filmService.unfavorite(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/review")
    ResponseEntity<Review> createReview(@PathVariable String id, @Valid @RequestBody ReviewCreateDto dto) {
        Review newReview = Review.builder()
                .text(dto.text())
                .rating(dto.rating())
                .build();

        Review response = filmService.createReview(id, newReview);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{imdbId}")
    ResponseEntity<Film> updateFilm(@PathVariable String imdbId, @Valid @RequestBody FilmUpdateDto dto) {
        Film film = Film.builder()
                .imdbId(imdbId)
                .title(dto.title())
                .releaseDate(dto.releaseDate())
                .genres(dto.genres())
                .overview(dto.overview())
                .posterPath(dto.posterPath())
                .build();

        Film response = filmService.update(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFilm(@PathVariable String id) {
        filmService.deleteFilm(id);
        return ResponseEntity.ok().build();
    }

    private User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }
}
