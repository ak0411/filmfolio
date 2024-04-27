package com.ak0411.repositories;

import com.ak0411.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository
        extends JpaRepository<Film, Integer> {
}
