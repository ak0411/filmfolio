package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record FilmCreateDto(
        @Pattern(regexp = "tt\\d+", message = "Film id should follow the imdbId format")
        String id,

        @NotBlank(message = "Film title cannot be empty")
        String title,

        @NotNull
        @YearValidator
        Integer year,

        @Enumerated
        Set<Genre> genre
) {
}
