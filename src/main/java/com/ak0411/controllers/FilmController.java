package com.ak0411.controllers;

import com.ak0411.exceptions.FilmNotFoundException;
import com.ak0411.entities.Film;
import com.ak0411.repositories.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
class FilmController {

    private final FilmRepository filmRepository;

    FilmController(FilmRepository repository) {
        this.filmRepository = repository;
    }

    @GetMapping
    List<Film> getAll() {
        return filmRepository.findAll();
    }

    @GetMapping("/{id}")
    Film getOne(@PathVariable Long id) {
        return filmRepository.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
    }

    @PostMapping
    Film add(@RequestBody Film request) {
        return filmRepository.save(request);
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
    void delete(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }
}
