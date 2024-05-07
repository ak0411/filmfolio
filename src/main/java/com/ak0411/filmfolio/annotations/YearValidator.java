package com.ak0411.filmfolio.annotations;

import com.ak0411.filmfolio.annotations.impl.YearValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = YearValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearValidator {
    String message() default "Invalid year";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}