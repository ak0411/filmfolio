package com.ak0411.filmfolio.annotations;

import com.ak0411.filmfolio.annotations.impl.EnumValidatorImpl;
import com.ak0411.filmfolio.annotations.impl.GenreValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EnumValidatorImpl.class, GenreValidatorImpl.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@NotNull
public @interface EnumValidator {
    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}