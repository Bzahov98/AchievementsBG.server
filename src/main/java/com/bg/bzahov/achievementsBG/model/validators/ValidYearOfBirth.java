package com.bg.bzahov.achievementsBG.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearOfBirthValidator.class)
public @interface ValidYearOfBirth {

    String ERROR_INVALID_YEAR_OF_BIRTH = "Invalid year of birth";

    String message() default ERROR_INVALID_YEAR_OF_BIRTH;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}