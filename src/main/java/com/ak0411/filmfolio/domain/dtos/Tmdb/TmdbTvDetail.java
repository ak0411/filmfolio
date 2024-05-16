package com.ak0411.filmfolio.domain.dtos.Tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmdbTvDetail {
    private Long id;
    private String name;
    private String overview;
    @JsonProperty("first_air_date")
    private String firstAirDate;
    @JsonProperty("genre_ids")
    private List<Integer> genreIds;
    @JsonProperty("poster_path")
    private String posterPath;
}
