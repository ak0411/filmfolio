package com.ak0411.filmfolio.repositories;

import com.ak0411.filmfolio.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository
        extends JpaRepository<Film, Long> {
}
