package com.ak0411.filmfolio.domain.dtos.Tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmdbMovieDetail {
    private Long id;
    private String title;
    private String overview;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    @JsonProperty("poster_path")
    private String posterPath;
}
