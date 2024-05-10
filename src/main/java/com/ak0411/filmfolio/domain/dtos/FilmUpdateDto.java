package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record FilmUpdateDto(
        @NotBlank(message = "Film title cannot be empty")
        String title,

        @NotNull
        @YearValidator
        Integer year,

        @Enumerated
        Set<Genre> genre
) {
}
