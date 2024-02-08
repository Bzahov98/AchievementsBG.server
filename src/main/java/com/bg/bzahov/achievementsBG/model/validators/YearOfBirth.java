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
public @interface YearOfBirth {
    String message() default "Invalid year of birth";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}