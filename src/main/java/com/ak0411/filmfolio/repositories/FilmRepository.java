package com.ak0411.filmfolio.repositories;

import com.ak0411.filmfolio.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository
        extends JpaRepository<Film, String> {
}
