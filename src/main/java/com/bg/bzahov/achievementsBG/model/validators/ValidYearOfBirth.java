package com.bg.bzahov.achievementsBG.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_YEAR_OF_BIRTH_RESTRICTION;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearOfBirthValidator.class)
public @interface ValidYearOfBirth {
    String message() default ERROR_YEAR_OF_BIRTH_RESTRICTION;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}