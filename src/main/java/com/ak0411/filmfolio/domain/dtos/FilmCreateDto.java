package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.annotations.EnumValidator;
import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

public record FilmCreateDto(
        @Pattern(regexp = "tt\\d{7}", message = "Film id should follow the imdbId format")
        String id,

        @NotBlank(message = "Film title cannot be empty")
        String title,

        @NotNull
        @YearValidator
        Integer year,

        @EnumValidator(enumClass = Genre.class)
        Set<Genre> genre
) {
}
