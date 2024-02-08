package com.bg.bzahov.achievementsBG.model.validators;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class YearOfBirthValidator implements ConstraintValidator<ValidYearOfBirth, Integer> {
    @Override
    public void initialize(ValidYearOfBirth constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return isAgeValid(yearOfBirth, currentYear) && yearOfBirth <= currentYear;
    }

    private static boolean isAgeValid(Integer yearOfBirth, int currentYear) {
        return currentYear - yearOfBirth < 100;
    }
}