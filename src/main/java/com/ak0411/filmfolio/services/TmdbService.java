package com.ak0411.filmfolio.services;

import com.ak0411.filmfolio.domain.entities.Film;
import reactor.core.publisher.Mono;

public interface TmdbService {
    Mono<Film> fetchFilmByImdbId(String imdbId);
}
