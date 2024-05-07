package com.ak0411.filmfolio.annotations.impl;

import com.ak0411.filmfolio.annotations.EnumValidator;
import com.ak0411.filmfolio.enums.Genre;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, Set<Genre>> {

    private Set<String> valueSet = null;

    @Override
    public boolean isValid(Set<Genre> values, ConstraintValidatorContext context) {
        if (values == null) {
            return true; // Null values are considered valid, assuming @NotNull annotation is also present
        }
        for (Genre value : values) {
            if (!valueSet.contains(value.toString().toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        valueSet = Arrays.stream(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::toString)
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }
}
