package com.ak0411.filmfolio.domain.dtos.Tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmdbResponse {
    @JsonProperty("movie_results")
    private List<TmdbMovieDetail> movieResults;
    @JsonProperty("tv_results")
    private List<TmdbTvDetail> tvResults;
}

