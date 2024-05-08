package com.ak0411.filmfolio.annotations.impl;

import com.ak0411.filmfolio.annotations.EnumValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenreValidatorImpl implements ConstraintValidator<EnumValidator, Set<Genre>> {

    Set<String> values;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Set<Genre> genres, ConstraintValidatorContext constraintValidatorContext) {
        if (genres == null) return true;

        for (Genre genre : genres) {
            if (!values.contains(genre.toString().toUpperCase())) {
                return false;
            }
        }
        return true;
    }

}
