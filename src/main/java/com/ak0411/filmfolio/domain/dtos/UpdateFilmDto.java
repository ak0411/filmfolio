package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.annotations.GenreValidator;
import com.ak0411.filmfolio.annotations.YearValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record UpdateFilmDto(

        @NotBlank(message = "Film title cannot be empty")
        String title,

        @NotNull
        @YearValidator
        Integer year,

        @GenreValidator(enumClass = Genre.class)
        Set<Genre> genre
) {
}
