package com.bg.bzahov.achievementsBG.model.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface ValidGender {

    String INVALID_GENDER_VALUE = "Invalid gender value";

    String message() default INVALID_GENDER_VALUE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
