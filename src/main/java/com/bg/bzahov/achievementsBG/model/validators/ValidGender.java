package com.bg.bzahov.achievementsBG.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.bg.bzahov.achievementsBG.constants.ErrorConstants.ERROR_INVALID_GENDER;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface ValidGender {
    String message() default ERROR_INVALID_GENDER;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
