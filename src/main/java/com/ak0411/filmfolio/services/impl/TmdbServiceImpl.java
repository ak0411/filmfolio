package com.ak0411.filmfolio.services.impl;

import com.ak0411.filmfolio.domain.dtos.Tmdb.TmdbMovieDetail;
import com.ak0411.filmfolio.domain.dtos.Tmdb.TmdbResponse;
import com.ak0411.filmfolio.domain.dtos.Tmdb.TmdbTvDetail;
import com.ak0411.filmfolio.domain.entities.Film;
import com.ak0411.filmfolio.services.TmdbService;
import com.ak0411.filmfolio.utils.GenreMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TmdbServiceImpl implements TmdbService {

    private final WebClient webClient;

    @Value("${api.tmdb-key}")
    private String API_KEY;

    @Autowired
    public TmdbServiceImpl(WebClient.Builder webClientBuilder) {
        String BASE_URL = "https://api.themoviedb.org/3";
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }

    public Mono<Film> fetchFilmByImdbId(String imdbId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/find/{imdbId}")
                        .queryParam("external_source", "imdb_id")
                        .queryParam("api_key", API_KEY)
                        .build(imdbId))
                .retrieve()
                .bodyToMono(TmdbResponse.class)
                .map(tmdbResponse -> convertToFilm(tmdbResponse, imdbId))
                .switchIfEmpty(Mono.error(new RuntimeException("No film found for IMDb ID: " + imdbId)));
    }

    private Film convertToFilm(TmdbResponse response, String imdbId) {
        log.info(String.valueOf(response));
        TmdbMovieDetail movieDetail = response.getMovieResults().isEmpty() ? null : response.getMovieResults().get(0);
        TmdbTvDetail tvDetail = response.getTvResults().isEmpty() ? null : response.getTvResults().get(0);

        if (movieDetail != null) {
            return Film.builder()
                    .imdbId(imdbId)
                    .title(movieDetail.getTitle())
                    .releaseDate(movieDetail.getReleaseDate())
                    .genres(convertGenres(movieDetail.getGenreIds()))
                    .overview(movieDetail.getOverview())
                    .posterPath(movieDetail.getPosterPath())
                    .build();
        } else if (tvDetail != null) {
            return Film.builder()
                    .imdbId(imdbId)
                    .title(tvDetail.getName())
                    .releaseDate(tvDetail.getFirstAirDate())
                    .genres(convertGenres(tvDetail.getGenreIds()))
                    .overview(tvDetail.getOverview())
                    .posterPath(tvDetail.getPosterPath())
                    .build();
        } else {
            throw new RuntimeException("No valid movie or TV show found in the response.");
        }
    }

    private List<String> convertGenres(List<Integer> genreIds) {
        return genreIds.stream()
                .map(GenreMapping::getGenre)
                .collect(Collectors.toList());
    }
}

