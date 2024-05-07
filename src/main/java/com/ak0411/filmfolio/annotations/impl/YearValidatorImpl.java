package com.ak0411.filmfolio.annotations.impl;

import com.ak0411.filmfolio.annotations.YearValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearValidatorImpl implements ConstraintValidator<YearValidator, Integer> {
    @Override
    public void initialize(YearValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = Year.now().getValue();
        return year != null && year <= currentYear;
    }
}
