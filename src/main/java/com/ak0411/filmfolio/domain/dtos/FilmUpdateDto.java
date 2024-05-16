package com.ak0411.filmfolio.domain.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FilmUpdateDto(
        @NotBlank
        String title,

        @NotBlank
        @JsonProperty("release_date")
        String releaseDate,

        List<String> genres,

        String overview,

        @JsonProperty("poster_path")
        String posterPath
) {
}
