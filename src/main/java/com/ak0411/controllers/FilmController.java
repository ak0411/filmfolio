package com.ak0411.controllers;

import com.ak0411.exceptions.FilmNotFoundException;
import com.ak0411.entities.Film;
import com.ak0411.repositories.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/films")
class FilmController {

    private final FilmRepository repository;

    FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Film> getAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    Film getOne(@PathVariable Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
    }

    @PostMapping
    Film add(@RequestBody Film request) {
        return repository.save(request);
    }

    @PutMapping("{id}")
    Film update(@PathVariable Integer id, @RequestBody Film request) {

        return repository.findById(id)
            .map(film -> {
                film.setTitle(request.getTitle());
                film.setYear(request.getYear());
                film.setGenre(request.getGenre());
                return repository.save(film);
            })
            .orElseGet(() -> {
                request.setId(id);
                return repository.save(request);
            });
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
