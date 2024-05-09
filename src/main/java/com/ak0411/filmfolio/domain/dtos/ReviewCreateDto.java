package com.ak0411.filmfolio.domain.dtos;

import com.ak0411.filmfolio.annotations.EnumValidator;
import com.ak0411.filmfolio.enums.Rating;
import jakarta.validation.constraints.NotNull;

public record ReviewCreateDto(
        String text,

        @NotNull
        @EnumValidator(enumClass = Rating.class)
        Rating rating
) {

}
